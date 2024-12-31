import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./Configure.css";

function Configure() {
  const location = useLocation();
  const navigate = useNavigate();
  const initialData = location.state || {};

  const [configData, setConfigData] = React.useState({
    inputFolder: "",
    outputFolder: "",
    inputQueue: "",
    outputQueue: "",
  });

  const handleChange = ({ target }) => {
    const { name, value } = target;
    setConfigData({ ...configData, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate("/Review", { state: { ...initialData, ...configData } });
  };

  return (
    <div className="configure-container">
      <h1>Configure Use Case</h1>
      <form className="configure-form" onSubmit={handleSubmit}>
        <label>
          Input folder:
          <input
            type="text"
            name="inputFolder"
            value={configData.inputFolder}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Output folder:
          <input
            type="text"
            name="outputFolder"
            value={configData.outputFolder}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Input Queue:
          <input
            type="text"
            name="inputQueue"
            value={configData.inputQueue}
            onChange={handleChange}
            required
          />
        </label>
        <label>
          Output Queue:
          <input
            type="text"
            name="outputQueue"
            value={configData.outputQueue}
            onChange={handleChange}
            required
          />
        </label>
        <button type="submit">Review</button>
      </form>
    </div>
  );
}

export default Configure;
