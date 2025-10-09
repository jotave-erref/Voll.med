// Arquivo: frontend/src/pages/LoginPage.tsx

// --- BLOCO 1: IMPORTAÇÕES ---
// Estamos importando duas ferramentas essenciais do React e o nosso conector da API.
import { useState, type FormEvent } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

// --- BLOCO 2: DEFINIÇÃO DO COMPONENTE ---
// definição da página de login
export function LoginPage() {

    // 2. Chame o hook para obter a função de navegação
    const navigate = useNavigate();


  // --- BLOCO 3: ESTADO DO COMPONENTE ---
  // Aqui cria "memórias" para o componente guardar o e-mail e a senha que o usuário digita.
  const [email, setEmail] = useState(''); // 'email' guarda o valor, 'setEmail' é a função para alterá-lo. Começa vazio.
  const [senha, setSenha] = useState(''); // 'senha' guarda o valor, 'setSenha' é a função para alterá-lo. Começa vazio.

  // --- BLOCO 4: FUNÇÃO DE SUBMISSÃO DO FORMULÁRIO ---
  // Esta função será executada quando o usuário clicar no botão "Entrar".
  // A palavra 'async' indica que esta função fará operações que podem demorar (como uma chamada de API).
  async function handleSubmit(event: FormEvent) {
    // 1. Previne o comportamento padrão do navegador, que é recarregar a página ao submeter um formulário.
    event.preventDefault();

    // 2. Usamos um bloco 'try...catch' para lidar com sucesso e erro da chamada da API.
    try {
      // 3. Fazendo a chamada POST para a rota '/login' da API.
      // O 'await' pausa a execução da função aqui até que a API responda.
      // Estamos enviando um objeto JSON com os campos 'login' e 'senha', exatamente como o backend espera.
      const response = await api.post('/login', {
        login: email, // O valor vem do estado 'email'
        senha: senha    // O valor vem do estado 'senha'
      });

      // 4. Se a chamada for bem-sucedida, o código continua daqui.
      const token = response.data.token;
      localStorage.setItem('vollmed_token', token);

      alert("Login realizado com sucesso!");

      navigate('/');

      // PRÓXIMOS PASSOS: Iremos salvar este token e redirecionar o usuário.

    } catch (error) {
      // 5. Se a API retornar um erro (ex: senha incorreta), o código cairá aqui.
      console.error("Erro no login:", error);
      alert("Falha no login. Verifique suas credenciais.");
    }
  }

  // --- BLOCO 5: O JSX (A ESTRUTURA VISUAL DA PÁGINA) ---
  // Este é o "HTML" da nossa página. Usamos classes do Tailwind CSS para estilizar.
  return (
    <div className="min-h-screen bg-gray-100 flex flex-col justify-center items-center">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">Login - Voll.med</h1>
        
        {/* O formulário está ligado à nossa função 'handleSubmit' */}
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="email" className="block text-gray-700 font-bold mb-2">E-mail</label>
            <input
              id="email"
              type="email"
              placeholder="Digite seu e-mail"
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={email} // O valor do campo está sempre ligado ao nosso estado 'email'
              onChange={event => setEmail(event.target.value)} // Quando o usuário digita, atualizamos o estado 'email'
            />
          </div>

          <div className="mb-6">
            <label htmlFor="password"  className="block text-gray-700 font-bold mb-2">Senha</label>
            <input
              id="password"
              type="password"
              placeholder="Digite sua senha"
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={senha} // O valor do campo está sempre ligado ao nosso estado 'senha'
              onChange={event => setSenha(event.target.value)} // Quando o usuário digita, atualizamos o estado 'senha'
            />
          </div>

          <button type="submit" className="w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-lg hover:bg-blue-600 transition duration-200">
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
}
