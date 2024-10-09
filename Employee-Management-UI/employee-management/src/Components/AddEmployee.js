import { useState } from 'react';

export default function AddEmployee() {

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [salary, setSalary] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const employeeData = {
      name: `${firstName} ${lastName}`,
      email: email,
      salary: salary,
    };
    try {
      const response = await fetch('http://localhost:8080/employees/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employeeData),
      });

      if (response.ok) {
        const result = await response.json();
        console.log('Employee added successfully:', result);
        setFirstName('');
        setLastName('');
        setEmail('');
        setSalary('');
      } else {
        console.error('Failed to add employee:', response.statusText);
      }
    } catch (error) {
      console.error('Error adding employee:', error);
    }
  };

  return (
    <div className="flex flex-col justify-center items-center mt-14">
      <h1 className="text-3xl text-gray-700 font-bold mb-8">USER FORM</h1>
      <form className="w-full max-w-md" onSubmit={handleSubmit}>

        <div className="flex justify-between mb-6">
          <div className="w-1/2 pr-2">
            <label className="block text-lg font-semibold text-gray-600 mb-2">First Name</label>
            <input
              type="text"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter first name"
              required
            />
          </div>
          <div className="w-1/2 pl-2">
            <label className="block text-lg font-semibold text-gray-600 mb-2">Last Name</label>
            <input
              type="text"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter last name"
              required
            />
          </div>
        </div>

        <div className="mb-6">
          <label className="block text-lg font-semibold text-gray-600 mb-2">Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full px-4 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter email"
            required
          />
        </div>

        <div className="mb-6">
          <label className="block text-lg font-semibold text-gray-600 mb-2">Salary</label>
          <input
            type="number"
            value={salary}
            onChange={(e) => setSalary(e.target.value)}
            className="w-full px-4 py-3 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="Enter salary"
            required
          />
        </div>

        <div className="flex justify-center">
          <button type="submit" className="bg-blue-500 text-white font-semibold px-6 py-2 rounded-md shadow hover:bg-blue-600 transition duration-200">
            Add Employee
          </button>
        </div>
      </form>
    </div>
  );
}
