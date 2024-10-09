import { useEffect, useState } from 'react';

export default function EmployeesList() {
  const [employees, setEmployees] = useState([]);
  const [editingEmployeeId, setEditingEmployeeId] = useState(null);
  const [editName, setEditName] = useState('');
  const [editEmail, setEditEmail] = useState('');
  const [editSalary, setEditSalary] = useState('');

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    const response = await fetch("http://localhost:8080/employees/details");
    const data = await response.json();
    setEmployees(data);
  };

  const handleEditClick = (employee) => {
    setEditingEmployeeId(employee.employee_id);
    setEditName(employee.name);
    setEditEmail(employee.email);
    setEditSalary(employee.salary);
  };

  const handleDeleteClick = async (employeeId) => {
    try {
      const response = await fetch(`http://localhost:8080/employees/remove?id=${employeeId}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        setEmployees((prevEmployees) => prevEmployees.filter((user) => user.employee_id !== employeeId));
      } else {
        console.error('Failed to delete employee:', response.statusText);
      }
    } catch (error) {
      console.error('Error deleting employee:', error);
    }
  };

  const handleUpdate = async (e, employeeId) => {
    e.preventDefault();

    const updatedEmployeeData = {
      name: editName,
      email: editEmail,
      salary: editSalary,
    };

    try {
      const response = await fetch(`http://localhost:8080/employees/update/${employeeId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedEmployeeData),
      });

      if (response.ok) {
        fetchEmployees();
        setEditingEmployeeId(null);
        setEditName('');
        setEditEmail('');
        setEditSalary('');
      } else {
        console.error('Failed to update employee:', response.statusText);
      }
    } catch (error) {
      console.error('Error updating employee:', error);
    }
  };

  return (
    <div className="p-6">
      <table className="table-auto w-full border-collapse shadow-lg rounded-lg overflow-hidden">
        <thead className="bg-gray-200">
          <tr>
            <th className="border px-4 py-2 text-left">Id</th>
            <th className="border px-4 py-2 text-left">Name</th>
            <th className="border px-4 py-2 text-left">Email</th>
            <th className="border px-4 py-2 text-left">Salary</th>
            <th className="border px-4 py-2 text-left">Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((user) => (
            <tr key={user.employee_id} className="border hover:bg-gray-100 transition duration-200">
              <td className="border px-4 py-2 text-left">{user.employee_id}</td>
              <td className="border px-4 py-2 text-left">
                {editingEmployeeId === user.employee_id ? (
                  <input
                    type="text"
                    value={editName}
                    onChange={(e) => setEditName(e.target.value)}
                    className="border rounded px-2 py-1"
                  />
                ) : (
                  user.name
                )}
              </td>
              <td className="border px-4 py-2 text-left">
                {editingEmployeeId === user.employee_id ? (
                  <input
                    type="email"
                    value={editEmail}
                    onChange={(e) => setEditEmail(e.target.value)}
                    className="border rounded px-2 py-1"
                  />
                ) : (
                  user.email
                )}
              </td>
              <td className="border px-4 py-2 text-left">
                {editingEmployeeId === user.employee_id ? (
                  <input
                    type="number"
                    value={editSalary}
                    onChange={(e) => setEditSalary(e.target.value)}
                    className="border rounded px-2 py-1"
                  />
                ) : (
                  user.salary
                )}
              </td>
              <td className="border px-4 py-2">
                {editingEmployeeId === user.employee_id ? (
                  <button
                    onClick={(e) => handleUpdate(e, user.employee_id)}
                    className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-400 transition duration-200 mr-2"
                  >
                    Save
                  </button>
                ) : (
                  <button
                    onClick={() => handleEditClick(user)}
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-400 transition duration-200 mr-2"
                  >
                    Edit
                  </button>
                )}
                <button
                  onClick={() => handleDeleteClick(user.employee_id)}
                  className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-400 transition duration-200"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
