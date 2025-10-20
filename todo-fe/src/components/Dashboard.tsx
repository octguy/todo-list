import React, { useState, useEffect } from "react";
import { useAuth } from "../hooks/useAuth";
import TaskList from "./Task/TaskList";
import TaskModal from "./Task/TaskModal";
import type { CreateTaskRequest, TaskResponse } from "../models/Task";
import { taskService } from "../services/taskService";

const Dashboard: React.FC = () => {
  const { user, logout } = useAuth();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [tasks, setTasks] = useState<TaskResponse[]>([]);
  // const [isLoading, setIsLoading] = useState(false);

  const fetchTasks = async () => {
    try {
      // setIsLoading(true);
      const fetchedTasks = await taskService.getAllTasks();
      setTasks(fetchedTasks.data);
    } catch (error) {
      console.error("Error fetching tasks:", error);
    } finally {
      // setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleAddTask = async (task: CreateTaskRequest) => {
    console.log("New task:", task);
    try {
      await taskService.createTask(task);
      await fetchTasks(); // Re-fetch tasks after successful creation
      setIsModalOpen(false); // Close modal on success
    } catch (error) {
      console.error("Error creating task:", error);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-4">
      <div className="max-w-4xl mx-auto">
        <div className="bg-white rounded-xl shadow-lg p-8">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-3xl font-bold text-gray-800">
              Welcome, {user?.username}!
            </h1>
            <button
              onClick={logout}
              className="bg-red-500 hover:bg-red-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-200"
            >
              Logout
            </button>
          </div>

          <div className="bg-gray-50 rounded-lg p-6">
            <h2 className="text-xl font-semibold text-gray-700 mb-4">
              User Information
            </h2>
            <div className="space-y-2">
              <p>
                <span className="font-medium">User ID:</span> {user?.userId}
              </p>
              <p>
                <span className="font-medium">Username:</span> {user?.username}
              </p>
              <p>
                <span className="font-medium">Email:</span> {user?.email}
              </p>
              {/* <p><span className="font-medium">Roles:</span> {user?.roleIds.join(', ')}</p> */}
            </div>
          </div>

          <div className="mt-6">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-semibold text-gray-700">Tasks</h2>
              <button
                onClick={() => setIsModalOpen(true)}
                className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-200 flex items-center gap-2"
              >
                <span className="text-xl">+</span>
                Add Task
              </button>
            </div>
            <TaskList tasks={tasks} />
          </div>
        </div>
      </div>

      <TaskModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSubmit={handleAddTask}
      />
    </div>
  );
};

export default Dashboard;
