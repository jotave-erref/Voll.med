// Arquivo: frontend/src/routes/index.tsx
import { createBrowserRouter } from "react-router-dom";
import { LoginPage } from "../pages/LoginPage";
import { DashboardPage } from "../pages/DashboardPage";
import { PrivateLayout } from "./PrivateLayout";
import { MedicosPage } from "../pages/MedicosPage";

export const router = createBrowserRouter([
  // rota pública
  {
    path: "/login",
    element: <LoginPage />,
  },
  //rotas privadas
  {
    element: <PrivateLayout />,
    children: [
      {
        path: "/",
        element: <DashboardPage />  
      },
      {
        path: "/medicos",
        element: <MedicosPage/>
      }
    ],

  },
]);