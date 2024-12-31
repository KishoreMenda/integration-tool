import React from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import "./Review.css";

function Review() {
  const location = useLocation();
  const data = location.state || {};
  const [message, setMessage] = React.useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/configurations",
        data
      );
      if (response.status === 200) {
        setMessage("Configuration submitted successfully!");
      } else {
        setMessage("Unexpected response from the server.");
      }
    } catch (error) {
      setMessage("Failed to submit configuration. Please try again.");
    }
  };

  return (
    <div className="review-container">
      <h1>Review Your Configuration</h1>
      <div className="review-data">
        <pre>{JSON.stringify(data, null, 2)}</pre>
      </div>
      <button className="review-submit-btn" onClick={handleSubmit}>
        Submit
      </button>
      {message && <p className="review-message">{message}</p>}
    </div>
  );
}

export default Review;
