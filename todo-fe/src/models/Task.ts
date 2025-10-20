export interface CreateTaskRequest {
  title: string;
  description: string;
  deadline: Date;
}

export interface TaskResponse {
  taskId: number;
  userId: number;
  title: string;
  description: string;
  deadline: Date;
  isCompleted: boolean;
  deletedAt: Date | null;
}

export interface UpdateTaskRequest {
  title?: string;
  description?: string;
  deadline?: Date;
  isCompleted?: boolean;
}
