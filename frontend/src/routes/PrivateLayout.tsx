
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

export function PrivateLayout() {
    
    // hook para verificar a autenticação
    const {isAuthenticated} = useAuth();

    console.log("Está autenticado?", isAuthenticated);

    // se o usuario não estiver logado...
    if(!isAuthenticated) {

        //... redirecionamos ele para pagina de login
        //<Navigate> faz isso declarativamente
        // 'replace' evita que a rota privada fica no historico do navegador
        return <Navigate to="/login" replace />;
    }

    // Se o usuario estiver autenticado renderiza o conteudo filho da rota
    // <Outlet> é um placeholder que diz: "renderize aqui qualquer
  // que seja a rota aninhada que corresponde à URL atual".
    return <Outlet/>;
}