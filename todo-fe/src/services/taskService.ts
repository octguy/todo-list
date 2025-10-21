import api from ".";
import type {
  CreateTaskRequest,
  TaskResponse,
  UpdateTaskRequest,
} from "../models/Task";
import type { ApiResponse } from "../models/ApiResponse";

export const taskService = {
  async createTask(
    request: CreateTaskRequest
  ): Promise<ApiResponse<TaskResponse>> {
    const response = await api.post<ApiResponse<TaskResponse>>(
      "/tasks",
      request
    );
    return response.data;
  },

  async getAllTasks(): Promise<ApiResponse<TaskResponse[]>> {
    const response = await api.get<ApiResponse<TaskResponse[]>>("/tasks");
    return response.data;
  },

  async updateTask(
    taskId: number,
    request: UpdateTaskRequest
  ): Promise<ApiResponse<TaskResponse>> {
    const response = await api.put<ApiResponse<TaskResponse>>(
      `/tasks/${taskId}`,
      request
    );
    return response.data;
  },
};
