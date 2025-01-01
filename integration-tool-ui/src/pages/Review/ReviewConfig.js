import React from "react";
import "./ReviewConfig.css";

const ReviewConfig = ({ config, setShowReview }) => {
  const handleEdit = () => {
    setShowReview(false);
  };

  const handleSubmit = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/configurations", {
        method: "POST", // Sending a POST request
        headers: {
          "Content-Type": "application/json", // Setting content type to JSON
        },
        body: JSON.stringify(config), // Sending the 'config' data as JSON in the request body
      });

      if (!response.ok) {
        throw new Error("Failed to submit configuration");
      }

      const responseData = await response.json(); // Get the response data
      console.log("Configuration Submitted:", responseData);

      alert("Configuration successfully submitted!");
    } catch (error) {
      console.error("Error submitting configuration:", error);
      alert("There was an error submitting the configuration.");
    }
  };

  return (
    <div className="review-config">
      <h2>Review Your Configuration</h2>
      <ul>
        <li>
          <strong>Source Type:</strong> {config.sourceType || "Not Specified"}
        </li>
        <li>
          <strong>Source Details:</strong>{" "}
          {config.sourceDetails || "Not Specified"}
        </li>
        <li>
          <strong>Trigger:</strong> {config.trigger || "None"}
        </li>
        {config.trigger === "triggerFile" && (
          <li>
            <strong>Trigger File:</strong>{" "}
            {config.triggerDetails || "Not Specified"}
          </li>
        )}
        <li>
          <strong>Action:</strong> {config.action || "Not Specified"}
        </li>
        <li>
          <strong>Destination Type:</strong>{" "}
          {config.destination || "Not Specified"}
        </li>
        <li>
          <strong>Destination Details:</strong>{" "}
          {config.destinationDetails || "Not Specified"}
        </li>
      </ul>
      <button onClick={handleEdit}>Edit Configuration</button>
      <button onClick={handleSubmit}>Confirm and Submit</button>
    </div>
  );
};

export default ReviewConfig;
