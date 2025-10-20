import React, { useEffect, useState } from "react";
import type {
  CreateTaskRequest,
  TaskResponse,
  UpdateTaskRequest,
} from "../../models/Task";

interface TaskModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmitCreate: (task: CreateTaskRequest) => void;
  onSubmitUpdate: (taskId: number, task: UpdateTaskRequest) => void;
  initialTask: TaskResponse | null; // Optional prop for editing existing task
}

const TaskModal: React.FC<TaskModalProps> = ({
  isOpen,
  onClose,
  onSubmitCreate,
  onSubmitUpdate,
  initialTask,
}) => {
  const isEdit = !!initialTask;
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [deadline, setDeadline] = useState("");
  const [isCompleted, setIsCompleted] = useState(false);

  useEffect(() => {
    if (initialTask) {
      setTitle(initialTask.title);
      setDescription(initialTask.description);
      setDeadline(
        initialTask.deadline
          ? new Date(initialTask.deadline).toISOString().slice(0, 16)
          : ""
      );
      setIsCompleted(initialTask.isCompleted);
    } else {
      setTitle("");
      setDescription("");
      setDeadline("");
      // setIsCompleted(false);
    }
  }, [initialTask]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // onSubmit({ title, description, deadline: new Date(deadline) });
    // setTitle("");
    // setDescription("");
    // setDeadline("");
    // onClose();
    if (isEdit && initialTask) {
      onSubmitUpdate(initialTask.taskId, {
        title,
        description,
        deadline: deadline ? new Date(deadline) : undefined,
        isCompleted,
      });
    } else {
      onSubmitCreate({
        title,
        description,
        deadline: deadline ? new Date(deadline) : new Date(),
      });
    }
  };

  if (!isOpen) return null;

  return (
    <div
      className="fixed inset-0 backdrop-blur-sm flex items-center justify-center z-50"
      onClick={onClose}
    >
      <div
        className="bg-white rounded-lg shadow-xl p-6 w-full max-w-md mx-4"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-2xl font-bold text-gray-800">
            {isEdit ? "Update Task" : "Add New Task"}
          </h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 text-2xl"
          >
            ×
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Title
            </label>
            <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Enter task title"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Description
            </label>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              rows={3}
              className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Enter task description"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Deadline
            </label>
            <input
              type="datetime-local"
              value={deadline}
              onChange={(e) => setDeadline(e.target.value)}
              className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
            />
          </div>

          {isEdit && (
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Completed
              </label>
              <div className="flex items-center">
                <button
                  type="button"
                  role="switch"
                  aria-checked={isCompleted}
                  onClick={() => setIsCompleted((v) => !v)}
                  className={`relative inline-flex items-center h-6 w-11 rounded-full transition-colors duration-200 focus:outline-none ${
                    isCompleted ? "bg-green-500" : "bg-gray-200"
                  }`}
                >
                  <span
                    className={`inline-block h-5 w-5 transform bg-white rounded-full shadow transition-transform duration-200 ${
                      isCompleted ? "translate-x-5" : "translate-x-0"
                    }`}
                  />
                </button>
                <span className="ml-3 text-sm text-gray-700">
                  {isCompleted ? "Yes" : "No"}
                </span>
              </div>
            </div>
          )}

          <div className="flex gap-3 pt-4">
            <button
              type="button"
              onClick={onClose}
              className="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded-lg transition-colors duration-200"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="flex-1 bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-200"
            >
              {isEdit ? "Update Task" : "Add Task"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default TaskModal;
