import React from "react";
import { useNavigate } from "react-router-dom";
import "./Home.css";

function Home() {
  const [formData, setFormData] = React.useState({
    sourceSystem: "",
    targetSystem: "",
    scenarioName: "",
  });

  const navigate = useNavigate();

  const handleChange = ({ target }) => {
    const { name, value } = target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate("/Configure", { state: formData });
  };

  return (
    <div className="home-container">
      <h1>Integration Tool</h1>
      <form className="home-form" onSubmit={handleSubmit}>
        <input
          type="text"
          name="sourceSystem"
          placeholder="Source System"
          value={formData.sourceSystem}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="targetSystem"
          placeholder="Target System"
          value={formData.targetSystem}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="scenarioName"
          placeholder="Scenario Name"
          value={formData.scenarioName}
          onChange={handleChange}
          required
        />
        <button type="submit">Create Flow</button>
      </form>
    </div>
  );
}

export default Home;
