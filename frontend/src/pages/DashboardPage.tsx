// Arquivo: frontend/src/pages/DashboardPage.tsx
import { useNavigate } from "react-router-dom";


export function DashboardPage() {

  const navigate = useNavigate();

  function handleLogout(){
    localStorage.removeItem('vollmed_token');
    navigate('/login');
  }


  return(
    
    <div>
        <h1>Dashboard - Usuário Logado</h1>;

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-bold py-2px-4 rounded"
        >
          Sair  
        </button>

    </div>
    

  )
}