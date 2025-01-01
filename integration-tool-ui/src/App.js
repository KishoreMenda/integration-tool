import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home/Home";
import RouteConfig from "./pages/RouteConfig/RouteConfig";
import ReviewConfig from "./pages/Review/ReviewConfig";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/RouteConfig" element={<RouteConfig />} />
        <Route path="/ReviewConfig" element={<ReviewConfig />} />
      </Routes>
    </Router>
  );
}

export default App;
