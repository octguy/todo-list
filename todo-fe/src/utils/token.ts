import type { AuthResponse } from "../models/Auth";

export const storageService = {
  setAccessToken(token: string): void {
    localStorage.setItem("accessToken", token);
  },

  getAccessToken(): string | null {
    return localStorage.getItem("accessToken");
  },

  removeAccessToken(): void {
    localStorage.removeItem("accessToken");
  },

  setUser(user: AuthResponse): void {
    localStorage.setItem("user", JSON.stringify(user));
  },

  getUser(): AuthResponse | null {
    const user = localStorage.getItem("user");
    return user ? JSON.parse(user) : null;
  },

  removeUser(): void {
    localStorage.removeItem("user");
  },

  clearAll(): void {
    localStorage.clear();
  },
};
