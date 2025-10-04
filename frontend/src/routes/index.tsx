// Arquivo: frontend/src/routes/index.tsx
import { createBrowserRouter } from "react-router-dom";
import { LoginPage } from "../pages/LoginPage";
import { DashboardPage } from "../pages/DashboardPage";

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/", // A rota raiz da nossa aplicação
    element: <DashboardPage />,
  },
]);