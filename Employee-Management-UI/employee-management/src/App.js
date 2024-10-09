import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import EmployeesList from './Components/EmployeesList';
import Navbar from './Components/Navbar';
import AddEmployee from './Components/AddEmployee';

function App() {
  return (
    <Router>
      <div>
        <Navbar />
        <Routes>
        <Route path="/" element={<EmployeesList />} />
        <Route path="/employees/add" element={<AddEmployee />} />
        </Routes>
      </div>
    </Router>

  );
}

export default App;
