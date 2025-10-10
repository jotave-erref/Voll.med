// Arquivo: frontend/src/pages/DashboardPage.tsx
import { useNavigate, Link } from "react-router-dom";


export function DashboardPage() {

  const navigate = useNavigate();

  function handleLogout(){
    localStorage.removeItem('vollmed_token');
    navigate('/login');
  }


  return(
    
    <div className="container mx-auto p-4">
        <h1 className="text-2xl font-bold">Dashboard - Usuário Logado</h1>;

        <div className="mt-4 flex space-x-4">
          <Link to="/medicos" className="bg-blue-500 text-white font-bold py-2 px-4 rounded hover:bg-blue-600">
            Gerenciar Médicos
          </Link>
        

          <button
            onClick={handleLogout}
            className="bg-red-500 text-white font-bold py-2 px-4 rounded hover:bg-red-600">
            Sair  
          </button>
        </div>
    </div>
    
  );
}