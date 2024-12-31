import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home/Home";
import Configure from "./pages/configure/Configure";
import Review from "./pages/Review/Review";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/Configure" element={<Configure />} />
        <Route path="/Review" element={<Review />} />
      </Routes>
    </Router>
  );
}

export default App;
