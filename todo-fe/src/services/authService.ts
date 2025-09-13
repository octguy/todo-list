import api from ".";
import type {
  AuthResponse,
  LoginRequest,
  RegisterRequest,
} from "../models/Auth";
import type { ApiResponse } from "../models/ApiResponse";

export const authService = {
  async login(credentials: LoginRequest): Promise<ApiResponse<AuthResponse>> {
    const response = await api.post<ApiResponse<AuthResponse>>(
      "/auth/login",
      credentials
    );
    return response.data;
  },

  async register(
    userData: RegisterRequest
  ): Promise<ApiResponse<AuthResponse>> {
    const response = await api.post<ApiResponse<AuthResponse>>(
      "/auth/register",
      userData
    );
    return response.data;
  },
};
