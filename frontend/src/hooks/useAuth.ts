//"Custom Hook", uma função que permite reutilizar lógica de estado
export const useAuth = () => {
    // Busca o token no localStorage.
    const token = localStorage.getItem('vollmed_token');

    // Verifica se o token existe e não é uma string vazia.
  // A dupla negação (!!) transforma o valor em um booleano (true/false).
  // Se 'token' for uma string com conteúdo, !!token será true.
  // Se 'token' for null ou "", !!token será false.
    const isAuthenticated = !!token;

    // O hook retorna um objeto com a informação se o usuário está autenticado.
    return {isAuthenticated};
}