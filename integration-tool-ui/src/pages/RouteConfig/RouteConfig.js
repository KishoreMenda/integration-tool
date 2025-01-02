import React, { useState } from "react";
import "./RouteConfig.css";
import ReviewConfig from "../Review/ReviewConfig";

const RouteConfig = () => {
  const [sourceType, setSourceType] = useState("");
  const [sourceDetails, setSourceDetails] = useState("");
  const [trigger, setTrigger] = useState("");
  const [triggerDetails, setTriggerDetails] = useState("");
  const [action, setAction] = useState("copy");
  const [destinationType, setDestinationType] = useState("");
  const [destinationDetails, setDestinationDetails] = useState("");
  const [showReview, setShowReview] = useState(false);

  const handleSubmit = () => {
    setShowReview(true); // Show the review page
  };

  const filteredConfig = {
    sourceType,
    sourceDetails,
    trigger,
    triggerDetails,
    action,
    destinationType,
    destinationDetails,
  };

  return (
    <div className="route-config">
      {!showReview ? (
        <>
          <h2>Configure Your Route</h2>

          <div>
            <label>Source:</label>
            <select
              value={sourceType}
              onChange={(e) => setSourceType(e.target.value)}
            >
              <option value="">Select Source Type</option>
              <option value="folder">Folder</option>
              <option value="queue">Queue</option>
            </select>
          </div>

          {sourceType === "folder" && (
            <div>
              <label>Source Folder:</label>
              <input
                type="text"
                value={sourceDetails}
                onChange={(e) => setSourceDetails(e.target.value)}
              />
            </div>
          )}

          {sourceType === "queue" && (
            <div>
              <label>Queue Name:</label>
              <input
                type="text"
                value={sourceDetails}
                onChange={(e) => setSourceDetails(e.target.value)}
              />
            </div>
          )}

          <div>
            <label>Trigger:</label>
            <select value={trigger} onChange={(e) => setTrigger(e.target.value)}>
              <option value="">None</option>
              <option value="fileExists">File Exists</option>
              <option value="triggerFile">Trigger File</option>
            </select>
          </div>

          {trigger === "triggerFile" && (
            <div>
              <label>Trigger File:</label>
              <input
                type="text"
                value={triggerDetails}
                onChange={(e) => setTriggerDetails(e.target.value)}
              />
            </div>
          )}

          <div>
            <label>Action:</label>
            <select value={action} onChange={(e) => setAction(e.target.value)}>
              <option value="copy">Copy</option>
              <option value="move">Move</option>
              <option value="split">Split</option>
              <option value="transform">Transform</option>
            </select>
          </div>

          <div>
            <label>Destination:</label>
            <select
              value={destinationType}
              onChange={(e) => setDestinationType(e.target.value)}
            >
              <option value="">Select Destination Type</option>
              <option value="folder">Folder</option>
              <option value="queue">Queue</option>
            </select>
          </div>

          {destinationType === "folder" && (
            <div>
              <label>Destination Folder:</label>
              <input
                type="text"
                value={destinationDetails}
                onChange={(e) => setDestinationDetails(e.target.value)}
              />
            </div>
          )}

          {destinationType === "queue" && (
            <div>
              <label>Queue Name:</label>
              <input
                type="text"
                value={destinationDetails}
                onChange={(e) => setDestinationDetails(e.target.value)}
              />
            </div>
          )}

          <button onClick={handleSubmit}>Save Route</button>
        </>
      ) : (
        <ReviewConfig config={filteredConfig} setShowReview={setShowReview} />
      )}
    </div>
  );
};

export default RouteConfig;
