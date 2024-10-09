import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <div>
      <nav className="flex justify-between items-center sticky top-0 z-50 shadow-lg bg-gradient-to-r from-gray-900 via-gray-800 to-gray-900 px-8 py-4 w-full border-b border-gray-700">
        <h1 className="text-2xl font-black tracking-wider text-white">
          Employee Management System
        </h1>
        <ul className="flex font-medium text-white items-center space-x-10 text-l">
          <li className="px-4 py-2 hover:text-gray-400 cursor-pointer transition duration-300 ease-in-out hover:bg-gray-800 rounded-md">
            <Link to="/">Home</Link>
          </li>
          <li className="px-4 py-2 hover:text-gray-400 cursor-pointer transition duration-300 ease-in-out hover:bg-gray-800 rounded-md">
            <Link to="/employees/add">Add Employee</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
}
