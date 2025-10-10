import { useQuery } from "@tanstack/react-query";
import api from "../services/api";


//formato de dados typescript para segurança e autocompletar
interface Medico{
    id: number;
    nome: string;
    email: string;
    crm: string;
    especialidade: string;
}


//função de busca de dados que faz a chamada na api
const fetchMedicos = async () => {

    // API axios faz uma requisição GET para /medicos
    // COm a configuração do interceptor o JWT é enviado automaticamente 
    const { data } = await api.get('/medicos');
    return data.content; // API tras os dados dentro do 'content' por conta por causa da paginação 
 };


 export function MedicosPage() {
    
    
    const { data, isLoading, isError } = useQuery<Medico[]> ({
        queryKey: ['medicos'], //chave unica dessa consulta
        queryFn: fetchMedicos, // função que será exeutada para buscar os dados
    });
    
    // ----- Lógica de renderização Condicional-----
    if(isLoading){
        return <span>Carregando...</span>
    }

    if(isError){
        <span>Ocorreu um error ao buscar os dados.</span>
    }

    return (
        <div className="container mx-auto p4">
            <h1 className="text-2xl font-bold mb-4">Gerenciamento de Médicos</h1>

            <table className="min-w-full bg-white border">
                <thead className="bg-gray-200">
                    <tr>
                        <th className="py-2 px-4 border-b">Nome</th>
                        <th className="py-2 px-4 border-b">Email</th>
                        <th className="py-2 px-4 border-b">CRM</th>
                        <th className="py-2 px-4 border-b">Especialidade</th>
                    </tr>
                </thead>
                <tbody>
                    
                    {/* o .map() para criar uma linha (<tr>) para cada médico nos dados */}
                    {data?.map(medico => (
                        <tr key={medico.id}>
                            <td className="py-2 px-4 border-b">{medico.nome}</td> 
                            <td className="py-2 px-4 border-b">{medico.email}</td> 
                            <td className="py-2 px-4 border-b">{medico.crm}</td> 
                            <td className="py-2 px-4 border-b">{medico.especialidade}</td>            
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
    
 }