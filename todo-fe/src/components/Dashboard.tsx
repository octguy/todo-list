import React from 'react';
import { useAuth } from '../hooks/useAuth';

const Dashboard: React.FC = () => {
  const { user, logout } = useAuth();

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-4">
      <div className="max-w-4xl mx-auto">
        <div className="bg-white rounded-xl shadow-lg p-8">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-3xl font-bold text-gray-800">Welcome, {user?.username}!</h1>
            <button
              onClick={logout}
              className="bg-red-500 hover:bg-red-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-200"
            >
              Logout
            </button>
          </div>
          
          <div className="bg-gray-50 rounded-lg p-6">
            <h2 className="text-xl font-semibold text-gray-700 mb-4">User Information</h2>
            <div className="space-y-2">
              <p><span className="font-medium">User ID:</span> {user?.userId}</p>
              <p><span className="font-medium">Username:</span> {user?.username}</p>
              <p><span className="font-medium">Email:</span> {user?.email}</p>
              {/* <p><span className="font-medium">Roles:</span> {user?.roleIds.join(', ')}</p> */}
            </div>
          </div>
          
          <div className="mt-6 text-center text-gray-600">
            <p>Todo application functionality will be added here.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;