package com.malimaquintino.javamongo.util;


import com.malimaquintino.javamongo.dto.ColumnInputDTO;
import com.malimaquintino.javamongo.dto.DatabaseInputDTO;
import com.malimaquintino.javamongo.dto.TableInputDTO;
import lombok.experimental.UtilityClass;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MockDataGen {

    private final List<String> dataBaseNames = List.of("OTERO", "MONET", "IA_TEST", "MAGNETO", "MAGENTO", "MILLIAN",
            "AMARO", "VIVA_BEM", "ANOMALIA", "LUDWICKING", "MAESTRIA", "ALFA", "MEGAZORD", "BRITALO", "JARDINS", "ACCAPULCO",
            "GAUGA", "JAMAL", "WEB_MOV", "ATROVEM", "ULTRA_JOVEM", "NEO_WEB", "SPUTINICK", "FRAM", "GIRA_MUNDO", "CARD_CRED",
            "ITA_TAX", "JINMES", "HOOK2B", "WORLD_DOMINATION", "Sinonyas", "Esxeowos", "Noisvuar", "Kozozosi", "Urnaekoy",
            "Biufovul", "Ashiavui", "Xyozalal", "Gueonzyo", "Ricleten", "Olgotafi", "Dubruern", "Voyvonai", "VOLARE",
            "KRIST_KROST", "MITRAL", "LOVE_MORE", "TENAK", "OLAF_MASTER", "GUINIL", "GEMINIE", "TAM", "DESTINEY",
            "LIFE_WAY", "AIR_JETS", "KMIKAZE", "DROP_THE_BOMB", "SOND_CORE", "MUSIC_STATION", "METRO2033",
            "SUMMER_FEST", "RAT_CAT", "FROID", "TRABUCO_DONOZOR", "MANJERICAO", "AMBROZIL", "TAMAINDO", "IOI201",
            "NEO_MILLENIUM", "VAM_MORE", "TRALES", "MONGOLIANO", "TRIBOLINA", "KRISTAL", "MONGOLIANA", "LOVINARIA",
            "foguete", "bandeira", "abacaxi", "animais", "sinopse", "sintaxe", "noitada", "cabra", "mesas", "porteiras",
            "auditor", "taxistas", "biologo", "alga_marinhas", "camarao", "jamaica", "natirutis", "livin4u", "yamls",
            "ioio", "n4s", "trenes", "cambage", "xoxo", "meli_plus", "project_kenia", "max_outputs", "sims", "glass",
            "mk112k", "GG_easy", "sandbox", "gimini_project", "portifolio", "ux_projects", "data_catalog", "red_cards",
            "users_blacklist", "data_search", "Gerenciamento_Clientes", "Controle_Estoque", "Sistema_Vendas",
            "Gerenciamento_Financeiro", "Controle_Reservas", "Agendamento_Consultas", "Analise_Dados_Vendas",
            "Gestao_RH", "Registro_Compras", "Controle_Pagamentos", "Controle_Faturamento", "Banco_De_Dados_Educacional",
            "Portal_Academico", "Plataforma_Streaming", "Controle_Producao", "Manutencao_Predial", "Logistica_Distribuicao",
            "Controle_Transportes", "Rastreamento_Encomendas", "Cadastro_Fornecedores", "Controle_Chamados", "Sistema_Tickets",
            "Sistema_Locacao", "Gerenciamento_Biblioteca", "Plataforma_EAD", "Ecommerce", "Automacao_Comercial",
            "Fintech", "Portal_Imobiliario", "Banco_De_Dados_Clinico", "Gerenciamento_Pacientes", "Controle_Medicamentos",
            "Aplicativo_Financeiro", "Plataforma_Jogos", "Sistema_Fidelidade", "Analise_Risco", "Gestao_Projetos",
            "ERP_Comercial", "Controle_Cobranca", "Gerenciamento_Contratos", "Banco_De_Dados_Hotel", "Portal_Turismo",
            "Gerenciamento_Eventos", "Gerenciamento_Producao", "Controle_Frota", "Analise_Desempenho",
            "Portal_Atendimento", "Gerenciamento_Franquias", "Controle_Orcamento", "Auditoria_Financeira",
            "Gerenciamento_Licitacoes", "Controle_Seguros", "Portal_Beneficios", "Plataforma_Recrutamento",
            "Controle_Ativos", "Sistema_De_Agendamento", "Portal_Do_Aluno", "Gestao_Estoque", "Rastreamento_Pacotes",
            "Controle_Producao_Industrial", "Plataforma_Ensino", "Sistema_Banco_Digital", "Sistema_Atendimento",
            "Plataforma_Suporte", "Sistema_Documental", "Portal_Pedidos", "Controle_Logistico", "Sistema_Despachante",
            "Plataforma_Delivery", "Controle_Visitas", "Plataforma_Cursos", "Controle_Cursos", "Plataforma_Consultoria",
            "Controle_Rotinas", "Plataforma_Treinamentos", "Controle_Acesso", "Sistema_De_Acesso", "Gestao_Documental",
            "Banco_De_Dados_Arquivistico", "Plataforma_Social", "Controle_Agendamentos", "Controle_Manutencao",
            "Plataforma_Integracao", "Banco_De_Dados_Seguranca", "Sistema_De_Veiculos", "Portal_Frotas", "Gestao_Hospitalar",
            "Plataforma_Recursos_Humanos", "Gestao_De_Pessoal", "Banco_De_Dados_Corporativo", "Plataforma_SaaS",
            "Controle_Arquivos", "Sistema_Gestao", "Controle_Empresas", "Portal_Fornecedores", "Plataforma_Documentos",
            "Banco_De_Dados_Contratos", "Controle_Compras", "Plataforma_Financeira", "Banco_De_Dados_Analitico",
            "Sistema_Cadastro", "Controle_Financeiro", "Plataforma_Bancos", "Controle_Reclamacoes", "Portal_De_Noticias",
            "Plataforma_Cobranca", "Controle_Servicos", "Sistema_Agendamento", "Plataforma_Atendimento", "Controle_Clientes",
            "Plataforma_Vendas", "Sistema_De_Servicos", "Banco_De_Dados_Empresarial", "Controle_Metricas", "Portal_Consultas",
            "Plataforma_Informacoes", "Gestao_Condominio", "Sistema_Financeiro", "Plataforma_Relatorios", "Banco_De_Dados_ERP",
            "Controle_Tarefas", "Portal_De_Projetos", "Sistema_Analitico", "Controle_Reservas_Hotel", "Banco_De_Dados_Tecnico",
            "Portal_De_Vendas", "Sistema_De_Financas", "Plataforma_Aluguel", "Controle_Devolucoes", "Controle_Parcelas",
            "Plataforma_Pagamentos", "Controle_Pagamentos_Fatura", "Portal_De_Orcamentos", "Controle_Contratos",
            "Plataforma_Desenvolvimento", "Sistema_ERP", "Gestao_De_Franquias", "Portal_De_Treinamento", "Banco_De_Dados_Treinamento",
            "Controle_De_Projetos", "Portal_De_Financas", "Sistema_Biblioteca", "Plataforma_Prestacao_Servicos", "Controle_Solicitacoes",
            "Portal_Documentos", "Banco_De_Dados_Consultoria", "Controle_Seguros_Saude", "Portal_Gestao", "Plataforma_Imobiliaria",
            "Controle_Imoveis", "Plataforma_Gestao_Tecnica", "Banco_De_Dados_Logistico", "Sistema_De_Logistica", "Controle_Transporte",
            "Controle_De_Frota", "Portal_Tecnologico", "Plataforma_Empresarial", "Gestao_Equipamentos", "Controle_Patrimonio",
            "Plataforma_Inventario", "Banco_De_Dados_Patrimonial", "Controle_De_Produtos", "Plataforma_Gestao_Estrategica",
            "Controle_Desempenho", "Portal_Informativo", "Banco_De_Dados_Atendimento", "Portal_Integracao", "Plataforma_Pesquisas",
            "Controle_Empregos", "Controle_Pontos", "Plataforma_Pontos", "Gestao_Pontos", "Controle_De_Contatos", "Portal_RH",
            "Banco_De_Dados_Contato", "Plataforma_Saude", "Sistema_Controle_Agendamento", "Portal_Clinico", "Controle_Clinico",
            "Banco_De_Dados_Hospitalar", "Portal_Reservas", "Plataforma_Hoteis", "Controle_Hospedagem", "Controle_Checkin",
            "Controle_Checkout", "Banco_De_Dados_Viagem", "Controle_Turismo", "Plataforma_De_Turismo", "Sistema_Controle_Reservas",
            "Controle_Vendas_Online", "Portal_Compras", "Controle_Vendas_Presencial", "Controle_Financas", "Plataforma_Gestao_Vendas",
            "Banco_De_Dados_Vendas", "Sistema_Seguros", "Controle_Seguros_Vida", "Plataforma_Seguros", "Controle_Polices",
            "Banco_De_Dados_Clientes", "Portal_Seguros", "Controle_Apolices", "Sistema_De_Seguros", "Plataforma_Analitica",
            "Gestao_De_Risco", "Controle_Analise", "Portal_De_Riscos", "Banco_De_Dados_Analise", "Plataforma_Analise",
            "Controle_Indicadores", "Plataforma_Desempenho", "Sistema_De_Desempenho", "Banco_De_Dados_Desempenho",
            "Plataforma_Fluxo", "Controle_Fluxo_Caixa", "Sistema_Fluxo", "Banco_De_Dados_Fluxo", "Controle_De_Desempenho",
            "Portal_De_Indicadores", "Plataforma_De_Indicadores", "Controle_Alocacao", "Sistema_Alocacao", "Plataforma_De_Alocacao",
            "Banco_De_Dados_Alocacao", "Portal_Alocacao", "Gerenciamento_Processos", "Controle_Recursos", "Banco_De_Dados_Financeiro",
            "Gestao_Suporte_Tecnico", "Sistema_Operacional_Empresarial", "Controle_De_Planejamento", "Banco_De_Dados_Operacional",
            "Gestao_Clientes_VIP", "Plataforma_Transacoes", "Controle_Documentos_Fiscais", "Gestao_Agendamentos", "Controle_Obras",
            "Banco_De_Dados_Obras", "Sistema_Planejamento", "Gestao_Calendario", "Controle_Planejamento_Financeiro",
            "Plataforma_Faturamento", "Gestao_Manutencao_Equipamentos", "Controle_Propriedades", "Banco_De_Dados_Imobiliario",
            "Gestao_Transportes", "Controle_Rotas", "Sistema_Distribuicao", "Banco_De_Dados_Distribuicao", "Controle_Fornecedores",
            "Gestao_Risco_Empresarial", "Sistema_Orcamento", "Controle_Planejamento_Estrategico", "Banco_De_Dados_Projetos",
            "Gestao_Cadeia_Suprimentos", "Controle_Prestadores_Servico", "Banco_De_Dados_Servicos", "Gestao_Veiculos",
            "Sistema_De_Reservas", "Controle_Consumo", "Banco_De_Dados_Consumo", "Gestao_Frotas", "Controle_Manutencao_Veicular",
            "Banco_De_Dados_Manutencao", "Controle_Aniversarios", "Gestao_Presentes_Corporativos", "Banco_De_Dados_Eventos",
            "Controle_Pedidos_Vendas", "Gestao_De_Ofertas", "Sistema_Descontos", "Controle_Credenciais", "Banco_De_Dados_Usuarios",
            "Gestao_Acesso_Seguro", "Controle_Pagamentos_Online", "Banco_De_Dados_Pagamentos", "Gestao_Investimentos",
            "Controle_Portfolio", "Sistema_De_Acoes", "Banco_De_Dados_Investimentos", "Gestao_Carteira", "Controle_Rendimentos",
            "Sistema_Consultoria", "Banco_De_Dados_Consultoria", "Gestao_Metas", "Controle_Objetivos", "Banco_De_Dados_Metas",
            "Controle_Indicadores_Chave", "Gestao_Qualidade", "Sistema_Avaliacao", "Banco_De_Dados_Qualidade", "Gestao_Relatorios",
            "Controle_Relatorios_Executivos", "Banco_De_Dados_Relatorios", "Controle_Reunioes", "Gestao_Participantes",
            "Sistema_De_Reunioes", "Banco_De_Dados_Reunioes", "Gestao_Historico", "Controle_Visitas_Tecnicas", "Banco_De_Dados_Visitas",
            "Controle_Agendamento_Tecnico", "Gestao_Software", "Controle_Atualizacoes", "Banco_De_Dados_Software",
            "Gestao_Configuracao", "Controle_Versoes", "Sistema_De_Desenvolvimento", "Banco_De_Dados_Codigo", "Gestao_Documentacao",
            "Controle_Policies", "Banco_De_Dados_Documentacao", "Gestao_Estrategica", "Controle_De_Processos",
            "Sistema_Operacional", "Banco_De_Dados_Operacoes", "Controle_Tecnologia", "Gestao_Desempenho_Tecnico",
            "Banco_De_Dados_Tecnologico", "Controle_Monitoramento", "Gestao_Redes", "Sistema_De_Redes", "Banco_De_Dados_Redes",
            "Controle_Politicas_De_Acesso", "Gestao_De_Dados", "Banco_De_Dados_Central", "Controle_Politicas_De_Seguranca",
            "Gestao_Cyberseguranca", "Sistema_De_Seguranca", "Banco_De_Dados_Seguranca", "Controle_Arquivos_Confidenciais",
            "Gestao_Servidores", "Sistema_De_Servidores", "Banco_De_Dados_Servidores", "Controle_Infraestrutura",
            "Gestao_Tecnologica", "Banco_De_Dados_Inovacao", "Controle_Projetos_Tecnologicos",
            "Gestao_Desenvolvimento", "Banco_De_Dados_Desenvolvimento", "Controle_Relacoes_Externas", "Gestao_Parcerias",
            "Banco_De_Dados_Parcerias", "Controle_Expansao", "Gestao_Internacionalizacao", "Sistema_De_Internacionalizacao",
            "Banco_De_Dados_Global", "Controle_Compliance", "Gestao_Conformidade", "Sistema_De_Compliance", "Banco_De_Dados_Compliance",
            "Controle_Legislacao", "Gestao_De_Legislacao", "Banco_De_Dados_Juridico", "Controle_Dados_Pessoais", "Gestao_LGPD",
            "Banco_De_Dados_Privacidade", "Controle_Politicas_De_Privacidade", "Gestao_Dados_Sensitivos", "Banco_De_Dados_Protegidos",
            "Controle_Planejamento_De_Projetos", "Gestao_Cronogramas", "Banco_De_Dados_Cronogramas", "Controle_Tempos_De_Execucao",
            "Gestao_Escopos", "Sistema_De_Projetos", "Banco_De_Dados_Escopos", "Controle_Impacto", "Gestao_Sustentavel",
            "Banco_De_Dados_Sustentabilidade", "Controle_Acoes_Verdes", "Gestao_Relacionamento", "Sistema_De_Clientes",
            "Banco_De_Dados_Relacionamento", "Controle_CRM", "Gestao_Leads", "Sistema_De_CRM", "Banco_De_Dados_CRM",
            "Controle_Atendimento_Cliente", "Gestao_Chatbot", "Sistema_De_Chatbot", "Banco_De_Dados_Chatbot", "Controle_BI",
            "Gestao_Inteligencia_Negocios", "Banco_De_Dados_BI", "Controle_Anomalias", "Gestao_Deteccao_De_Fraude", "Sistema_De_Fraude",
            "Banco_De_Dados_Fraude", "Controle_Consultas_Medicas", "Gestao_Agendamento_Medico", "Sistema_De_Saude", "Banco_De_Dados_Saude",
            "Controle_Medicamentos", "Gestao_Prescricao", "Banco_De_Dados_Prescricao", "Controle_Pacientes", "Gestao_Prontuarios",
            "Sistema_De_Prontuarios", "Banco_De_Dados_Prontuarios", "Controle_Internacoes", "Gestao_Hospitalar_Integral",
            "Banco_De_Dados_Hospitalar", "Controle_Consultas_Externas", "Gestao_Enfermagem", "Sistema_De_Enfermagem",
            "Banco_De_Dados_Enfermagem", "Controle_Cirurgias", "Gestao_Sala_Cirurgica", "Banco_De_Dados_Cirurgias", "Controle_Exames",
            "Gestao_Laboratorio", "Sistema_De_Exames", "Banco_De_Dados_Laboratorio", "Controle_Laudos", "Gestao_Imagem", "Sistema_De_Imagem_Medica",
            "Banco_De_Dados_Imagens", "Controle_Fisioterapia", "Gestao_Reabilitacao", "Sistema_De_Fisioterapia", "Banco_De_Dados_Reabilitacao",
            "Controle_Pacotes_Financeiros", "Gestao_De_Contratos", "Banco_De_Dados_Financas", "Controle_Documentos_Contabeis",
            "Gestao_De_Contabilidade", "Sistema_De_Contabilidade", "Banco_De_Dados_Contabilidade", "Controle_Auditorias",
            "Gestao_Auditoria_Interna", "Banco_De_Dados_Auditoria", "Controle_Inspecoes", "Gestao_Qualidade_Interna", "Sistema_De_Inspecao",
            "Banco_De_Dados_Inspecao", "Controle_Processos_Legais", "Gestao_De_Litigios", "Sistema_De_Processos", "Banco_De_Dados_Juridico",
            "Controle_Recursos_Humanos", "Gestao_Talentos", "Sistema_De_RH", "Banco_De_Dados_RH", "Controle_Treinamentos", "Gestao_De_Carreira",
            "Sistema_De_Treinamento", "Banco_De_Dados_Treinamento", "Controle_Beneficios", "Gestao_De_Planos", "Sistema_De_Beneficios",
            "Banco_De_Dados_Beneficios", "Controle_Contratos_Externos", "Gestao_Terceiros", "Sistema_De_Terceirizacao",
            "Banco_De_Dados_Terceiros", "Controle_Licitacoes", "Gestao_De_Compras_Publicas", "Sistema_De_Licitacoes",
            "Banco_De_Dados_Licitacoes", "Controle_Inventarios", "Gestao_De_Almoxarifado", "Sistema_De_Inventario",
            "Banco_De_Dados_Inventario", "Controle_Desenvolvimento_De_Produtos", "Gestao_Protótipos", "Sistema_De_P&D",
            "Banco_De_Dados_P&D", "Controle_Projetos_Especializados", "Gestao_De_Projetos_Especializados", "Sistema_De_Inovacao",
            "Banco_De_Dados_Inovacao", "Controle_De_Certificacoes", "Gestao_Acreditacao", "Sistema_De_Certificacao",
            "Banco_De_Dados_Certificacao", "Controle_Fabricacao", "Gestao_Industrial", "Sistema_De_Producao", "Banco_De_Dados_Producao",
            "Controle_Manutencao_Preventiva", "Gestao_De_Maquinas", "Sistema_De_Maquinario", "Banco_De_Dados_Maquinas",
            "Controle_Riscos_De_Seguranca", "Gestao_De_Desastres", "Sistema_De_Contingencia", "Banco_De_Dados_Seguranca",
            "Controle_Politicas_Empresariais", "Gestao_De_Regulamentacao", "Sistema_De_Politicas", "Banco_De_Dados_Regulamentacao",
            "Controle_Capacitacoes", "Gestao_Educacional", "Sistema_De_Treinamentos", "Banco_De_Dados_Educacional",
            "Controle_Certificados", "Gestao_De_Formacoes", "Sistema_De_Certificacoes", "Banco_De_Dados_Formacoes",
            "Controle_Agendamentos_Corporativos", "Gestao_Coordenacao", "Sistema_De_Agendamentos", "Banco_De_Dados_Coordenacao",
            "Controle_Documentacao_Legal", "Gestao_De_Permissoes", "Sistema_De_Permissoes", "Banco_De_Dados_Permissoes",
            "Controle_Vistorias", "Gestao_Inspecoes_Externas", "Sistema_De_Vistorias", "Banco_De_Dados_Vistorias",
            "Controle_Acordos", "Gestao_Convenios", "Sistema_De_Acordos", "Banco_De_Dados_Convenios", "Controle_Atendimento_Online",
            "Gestao_Suporte_Virtual", "Sistema_De_Suporte", "Banco_De_Dados_Suporte", "Controle_Agendamentos_Automaticos",
            "Gestao_Automatizada", "Sistema_De_Agendamento_Automatico", "Banco_De_Dados_Automatico", "Controle_Reservas_De_Equipamentos",
            "Gestao_Logistica_De_Materiais", "Sistema_De_Logistica", "Banco_De_Dados_Materiais", "Controle_Producao_Avancada",
            "Gestao_De_Operacoes", "Sistema_Operacional_Avancado", "Banco_De_Dados_Operacional", "Controle_Desempenho_Individual",
            "Gestao_Avaliacao_De_Desempenho", "Sistema_De_Avaliacao", "Banco_De_Dados_Avaliacao", "Controle_Planos_De_Acao",
            "Gestao_De_Metas_Estrategicas", "Sistema_De_Planos", "Banco_De_Dados_Planos", "Controle_Atendimento_Presencial",
            "Gestao_De_Filas", "Sistema_De_Gerenciamento_De_Filas", "Banco_De_Dados_Filas", "Controle_Gestor_Tecnico",
            "Gestao_De_Tecnicos", "Sistema_De_Tecnicos", "Banco_De_Dados_Tecnicos", "Controle_Relacionamento_Com_Parceiros",
            "Gestao_De_Colaboracoes", "Sistema_De_Parcerias", "Banco_De_Dados_Colaboracoes", "Controle_Cadastro_De_Funcionarios",
            "Gestao_De_Recursos_Humanos", "Sistema_De_Funcionarios", "Banco_De_Dados_Funcionarios", "Controle_Saude_E_Seguranca",
            "Gestao_De_Seguranca_Ocupacional", "Sistema_De_Saude_Ocupacional", "Banco_De_Dados_Saude_Ocupacional"
    );

    private List<String> tablesNames = List.of("CAVALO", "MAMACO", "VACALO", "TAX", "TABUCO", "GULHOTINA",
            "DEBIT", "PAY_MORE", "FRUITAS", "XAROPE", "RATOS", "USUARIOS", "ENDERECO", "PATHS", "IMAGENS", "COISAS",
            "NEGOCIO", "BUSINESS", "GENERO", "HOMEM_MULHER", "ENDERECO_ELETRONICO", "FABRICAS", "CORES", "FLORES",
            "TIPOS", "VENDAS", "REPORTS", "TEXT_CHOISE", "ELEMENTOS", "GARGANTAS", "TOENS", "CONTA", "IPS", "MASAS",
            "LOG", "SYSTEM_EMAIL", "EMAIL", "INTERNAL", "LIRAS", "SEMENTES", "TERRENO", "GEOMETRIA", "JUSTICA",
            "CALIBRE", "DRONE", "VEICULOS", "LISTAS", "RANK", "CLIENTES", "PRODUTOS", "VENDAS", "USUARIOS", "PEDIDOS",
            "PAGAMENTOS", "TRANSAÇÕES", "LOGS", "AUDITORIA", "FUNCIONARIOS", "FORNECEDORES", "ESTOQUE", "CATEGORIAS",
            "SUBCATEGORIAS", "PROMOCOES", "DESCONTOS", "ENDERECOS", "CUPONS", "ENTREGAS", "NOTAS_FISCAIS", "ITENS_PEDIDO",
            "DEVOLUCOES", "REEMBOLSOS", "AVALIACOES", "FEEDBACK", "MENSAGENS", "CHAT", "SESSOES", "CONFIGURACOES",
            "PERMISSOES", "ROLES", "GRUPOS", "TAREFAS", "PROJETOS", "ETAPAS", "PRIORIDADES", "STATUS", "RELATORIOS",
            "ANEXOS", "DOCUMENTOS", "FATURAS", "CONTAS", "RECIBOS", "SALDOS", "TRANSACOES_FINANCEIRAS", "LANCAMENTOS",
            "INVENTARIO", "DEPARTAMENTOS", "SETORES", "PRODUTOS_DIGITAIS", "PRODUTOS_FISICOS", "ASSINATURAS", "PLANOS",
            "SERVIÇOS", "TIPOS_PAGAMENTO", "EVENTOS", "RESERVAS", "CHECKIN", "CHECKOUT", "HISTORICO", "AGENDAMENTOS",
            "CAMPANHAS", "EMAILS", "SMS", "NOTIFICACOES", "LISTAS", "CONTATOS", "TELEFONES", "SUPORTE", "CHAMADOS",
            "INCIDENTES", "SOLICITACOES", "APROVACAO", "ERROS", "ALERTAS", "MONITORAMENTO", "METRICAS", "BI",
            "DASHBOARDS", "WIDGTS", "ANALISES", "MAPAS", "COORDENADAS", "LOCAIS", "ROTA", "PONTOS_INTERESSE",
            "PARCEIROS", "LICENÇAS", "CERTIFICADOS", "EXAMES", "CURSOS", "INSCRICOES", "PARTICIPANTES", "PRESENCA",
            "NOTAS", "BOLETIM", "TREINAMENTOS", "HABILIDADES", "COMPETENCIAS", "OBJETIVOS", "MISSÕES",
            "BONIFICACOES", "RECOMPENSAS", "ACHIEVEMENTS", "USUARIOS_BANIDOS", "USUARIOS_ATIVOS", "SESSOES_ATIVAS",
            "TOKENS", "VERIFICACOES", "2FA", "BACKUPS", "RESTAURACOES", "MIGRACOES", "REVISOES", "COMPARTILHAMENTO",
            "PERFIS", "FOTOS", "VIDEOS", "ARQUIVOS", "PASTAS", "PERGUNTAS", "RESPOSTAS", "TESTES", "EXERCICIOS",
            "PLANO_ESTUDOS", "AGENDA", "REUNIOES", "MINUTAS", "PAUTAS", "ANOTACOES", "LIVROS", "ARTIGOS", "REVISTAS",
            "BIBLIOTECA", "EMPRESTIMOS", "DEVOLUCOES_LIVROS", "MULTAS", "USO_SISTEMA", "ACESSOS", "LOGIN", "LOGOUT",
            "REDEFINICOES_SENHA", "POLITICAS", "REGULAMENTOS", "LICITACOES", "EDITAIS", "CONTRATOS", "ACORDOS",
            "NEGOCIACOES", "PROPOSTAS", "ORCAMENTOS", "COTACOES", "FORNECIMENTOS", "PARCELAS", "PAGAMENTOS_PENDENTES",
            "COMPRAS", "PRODUTOS_RETIRADOS", "ESTOQUE_BAIXO", "ESTOQUE_EXCESSO", "EXPIRACOES", "LOTES", "GRUPOS_COMPRAS",
            "ATENDIMENTOS", "INSUMOS", "COMPOSICOES", "TEMPOS", "CALENDARIO", "FERIADOS", "ESCALAS", "FREQUENCIA",
            "HORARIOS", "PONTO", "MARCACOES", "ABSENCIAS", "AUSENCIAS", "RELACOES", "CLIENTES_POTENCIAIS", "PROSPECÇÃO",
            "SEQUENCIAS", "CICLOS", "RECORRENCIA", "COMPARATIVOS", "ESTUDOS", "PESQUISAS", "RESULTADOS", "ESTATISTICAS",
            "TENDENCIAS", "PERSPECTIVAS", "PREVISOES", "SIMULACOES", "PLANOS_ACAO", "ESTRATEGIAS", "METAS", "KPIS",
            "ATRIBUTOS", "CARACTERISTICAS", "ESPECIFICACOES", "MODELOS", "SERIES", "VERSOES", "FABRICANTES", "MARCA",
            "TIPOS", "CLASSES", "CODIGOS", "REFERENCIAS", "REGISTROS", "IDENFICADORES", "SEQUENCIAS", "SERIAIS",
            "NUMEROS", "PATTERNS", "FORMATOS", "ARQUIVOS_TEMP", "ARQUIVOS_LOGS", "LOGS_ERROS", "LOGS_USO",
            "LOGS_ACESSO", "LOGS_TRANSACOES", "HISTORICO_ACESSO", "HISTORICO_PEDIDOS", "HISTORICO_COMPRAS",
            "ITENS_ESTOQUE", "ITENS_VENDIDOS", "ITENS_DANIFICADOS", "ITENS_EXCLUIDOS", "ITENS_PROMOCAO",
            "PRODUTOS_INATIVOS", "PRODUTOS_ATIVOS", "PRODUTOS_RETORNO", "VENDAS_ONLINE", "VENDAS_PRESENCIAIS",
            "CLIENTES_CORPORATIVOS", "CLIENTES_PF", "CLIENTES_PJ", "CLIENTES_INADIMPLENTES", "CLIENTES_VIP",
            "CLIENTES_REGULARES", "CLIENTES_FREQUENTES", "AVALIACOES_CLIENTES", "CRITICAS", "OPINIOES", "COMENTARIOS",
            "RESPOSTAS_CLIENTES", "FEEDBACK_USUARIOS", "PESQUISAS_SATISFACAO", "METRICAS_USO", "METRICAS_VENDAS",
            "RELATORIOS_DETALHADOS", "RELATORIOS_GERAIS", "RELATORIOS_FINANCEIROS", "RELATORIOS_OPERACIONAIS",
            "RELATORIOS_GESTAO", "RELATORIOS_COMERCIAL", "RELATORIOS_AUDITORIA", "NOTIFICACOES_CLIENTES",
            "NOTIFICACOES_ADMIN", "ALERTAS_USO", "ALERTAS_CRITICOS", "EVENTOS_ESPECIAIS", "EVENTOS_AGENDADOS",
            "RESERVAS_CONFIRMADAS", "RESERVAS_PENDENTES", "CHECKIN_CLIENTES", "CHECKOUT_CLIENTES", "CONSULTAS",
            "CONSULTAS_MEDICAS", "CONSULTAS_TECNICAS", "AGENDAMENTOS_CONSULTAS", "AGENDAMENTOS_VISITAS", "VISITAS_TECNICAS",
            "VISITAS_COMERCIAIS", "FATURAMENTO_MENSAL", "FATURAMENTO_ANUAL", "RECEITAS", "DESPESAS", "DESPESAS_FIXAS",
            "DESPESAS_VARIAVEIS", "LUCRO_BRUTO", "LUCRO_LIQUIDO", "MARGEM_LUCRO", "IMPOSTOS", "TAXAS", "TRIBUTOS",
            "CONTAS_A_RECEBER", "CONTAS_A_PAGAR", "RECEBIMENTOS", "PAGAMENTOS", "PAGAMENTOS_CONFIRMADOS", "PAGAMENTOS_PENDENTES",
            "PAGAMENTOS_CANCELADOS", "REEMBOLSOS_APROVADOS", "REEMBOLSOS_REJEITADOS", "DEVOLUCOES_APROVADAS",
            "DEVOLUCOES_PENDENTES", "SUPORTE_TECNICO", "SUPORTE_CLIENTES", "ATENDIMENTOS_FINALIZADOS", "ATENDIMENTOS_PENDENTES",
            "ATENDIMENTOS_ABERTOS", "INCIDENTES_RECORRENTES", "INCIDENTES_CRITICOS", "INCIDENTES_MEDIOS", "INCIDENTES_BAIXOS",
            "CHAMADOS_ABERTOS", "CHAMADOS_FECHADOS", "SOLICITACOES_PROCESSADAS", "SOLICITACOES_PENDENTES",
            "SOLICITACOES_CANCELADAS", "APROVACAO_PEDIDOS", "APROVACAO_CONTRATOS", "CONTRATOS_ATIVOS", "CONTRATOS_INATIVOS",
            "EDITAIS_ABERTOS", "EDITAIS_FECHADOS", "LICITACOES_PUBLICAS", "LICITACOES_PRIVADAS", "OFERTAS",
            "PROPOSTAS_RECEBIDAS", "PROPOSTAS_ENVIADAS", "NEGOCIACOES_CONCLUIDAS", "NEGOCIACOES_PENDENTES",
            "NEGOCIACOES_CANCELADAS", "PREÇOS", "DESCONTOS_APLICADOS", "DESCONTOS_EXPIRADOS", "PROMOCOES_ATIVAS",
            "PROMOCOES_INATIVAS", "MARKETING", "CAMPAINHAS_MARKETING", "PUBLICIDADE", "ANALISE_DE_MERCADO",
            "ESTUDOS_CONCORRENCIA", "TENDENCIAS_MERCADO", "PESQUISAS_PUBLICAS", "RESEARCH_INTERNO", "BENCHMARKS",
            "INDICADORES_PERFORMANCE", "INDICADORES_SATISFACAO", "KPI_SETORES", "KPI_ANUAL", "RESULTADOS_FINAIS",
            "RESULTADOS_PARCIAIS", "PLANOS_AJUSTES", "PLANEJAMENTO", "OBJETIVOS_CORPORATIVOS", "OBJETIVOS_OPERACIONAIS",
            "OBJETIVOS_TATICOS", "PLANO_DE_ACAO", "ESTRATEGIAS_APLICADAS", "ESTRATEGIAS_FUTURAS", "PROJECOES", "PREVISOES_FINANCEIRAS",
            "ANALISE_DE_RISCOS", "RISCOS_OPERACIONAIS", "RISCOS_FINANCEIROS", "SEGUROS", "SEGUROS_ATIVOS", "APOLICES",
            "APOLICES_CANCELADAS", "APOLICES_PENDENTES", "RECLAMACOES", "RECLAMACOES_CLIENTES", "RECLAMACOES_SOLUCIONADAS",
            "RECLAMACOES_PENDENTES", "ERROS_SISTEMA", "ERROS_APLICACAO", "BUGS_REPORTADOS", "CORRECOES_APLICADAS",
            "ATUALIZACOES_SISTEMA", "VERSOES_APLICACAO", "RELEASES", "MUDANCAS", "REGISTROS_MUDANCAS", "BACKUPS_COMPLETOS",
            "BACKUPS_INCRMENTAIS", "RESTAURACOES_BANCO", "RESTAURACOES_SISTEMA", "LOGS_BACKUPS", "LOGS_ATUALIZACOES",
            "RELATORIOS_SEGURANCA"
    );

    private List<String> columnNames = List.of("id", "idt", "category_fk", "user_fk", "sys_fk", "date_create", "date_update",
            "date_delete", "name", "address", "phone", "age", "credit", "username", "email", "status", "mother_name", "sign",
            "email", "size", "unit", "total", "radius", "max_size", "min_time", "balance", "account", "model", "sponsor",
            "date", "voltage", "amount", "counter", "number", "fuel", "key", "technology", "description", "comment",
            "city", "zip_code", "country", "is_active", "is_false", "is_true", "quantity", "color", "transmission",
            "type", "material", "fk", "uk", "line", "price", "speed", "major", "minor", "patch", "street_name",
            "image", "image_type", "brz", "xpto", "free", "tax", "bill", "so", "music", "primary", "part", "monitor",
            "car", "bike", "plane", "ID", "NOME", "DESCRICAO", "DATA_CRIACAO", "DATA_ATUALIZACAO", "STATUS", "VALOR", "PRECO",
            "QUANTIDADE", "CODIGO", "TIPO", "ENDERECO", "CIDADE", "ESTADO", "CEP", "PAIS", "TELEFONE", "EMAIL", "SENHA", "USUARIO",
            "DATA_NASCIMENTO", "CPF", "CNPJ", "RAZAO_SOCIAL", "NOME_FANTASIA", "INSCRICAO_ESTADUAL", "INSCRICAO_MUNICIPAL",
            "DATA_PEDIDO", "DATA_ENTREGA", "DATA_VENCIMENTO", "VALOR_TOTAL", "DESCONTO", "IMPOSTO", "SUBTOTAL", "NUMERO_PEDIDO",
            "NUMERO_FATURA", "FORMA_PAGAMENTO", "METODO_ENVIO", "PESO", "TAMANHO", "COR", "CATEGORIA", "SUBCATEGORIA", "MARCA",
            "MODELO", "SERIE", "FABRICANTE", "DATA_COMPRA", "DATA_VENDA", "COMENTARIO", "AVALIACAO", "NOTA", "QUANTIDADE_ESTOQUE",
            "QUANTIDADE_VENDIDA", "QUANTIDADE_DEVOLVIDA", "QUANTIDADE_MINIMA", "QUANTIDADE_MAXIMA", "LOCALIZACAO", "SECAO",
            "DEPARTAMENTO", "SETOR", "RESPONSAVEL", "GERENTE", "SUPERVISOR", "TIPO_CONTRATO", "DATA_INICIO", "DATA_TERMINO",
            "SALARIO", "CARGA_HORARIA", "CODIGO_INTERNO", "CODIGO_BARRAS", "REFERENCIA", "LOTE", "NUMERO_SERIE", "VALIDADE",
            "DATA_FABRICACAO", "DATA_EXPIRACAO", "PRIORIDADE", "STATUS_PEDIDO", "STATUS_PAGAMENTO", "STATUS_ENVIO", "RANKING",
            "POSICAO", "DATA_INSCRICAO", "DATA_PARTICIPACAO", "DATA_CADASTRO", "DATA_APROVACAO", "DATA_REJEICAO", "MOTIVO_REJEICAO",
            "MOTIVO_DEVOLUCAO", "MOTIVO_CANCELAMENTO", "TIPO_CLIENTE", "TIPO_FORNECEDOR", "TIPO_PRODUTO", "TIPO_SERVICO",
            "TIPO_TRANSAÇÃO", "TIPO_CONTA", "TIPO_DOCUMENTO", "TIPO_ENDERECO", "TIPO_TELEFONE", "TIPO_EMAIL", "PRIMEIRO_NOME",
            "ULTIMO_NOME", "NOME_COMPLETO", "ALIAS", "URL", "CAMINHO", "NOME_ARQUIVO", "EXTENSAO_ARQUIVO", "TAMANHO_ARQUIVO",
            "DATA_UPLOAD", "DATA_MODIFICACAO", "DATA_EXCLUSAO", "MOTIVO_EXCLUSAO", "STATUS_CONTA", "STATUS_LOGIN", "DATA_LOGIN",
            "DATA_LOGOUT", "NUMERO_ACESSOS", "ULTIMO_ACESSO", "IP_ACESSO", "DISPOSITIVO", "NAVEGADOR", "SISTEMA_OPERACIONAL",
            "TIPO_SESSAO", "DATA_SESSAO", "CHAVE_SESSAO", "ID_SESSAO", "CODIGO_VERIFICACAO", "DATA_VERIFICACAO",
            "DATA_EXPIRACAO_CODIGO", "TOKEN", "TOKEN_RENOVACAO", "TOKEN_EXPIRACAO", "DATA_ATIVACAO", "DATA_DESATIVACAO",
            "STATUS_ATIVACAO", "STATUS_BLOQUEIO", "MOTIVO_BLOQUEIO", "DATA_BLOQUEIO", "DATA_DESBLOQUEIO", "NOME_PAI", "NOME_MAE",
            "NOME_RESPONSAVEL", "DATA_EMISSAO", "ORGAO_EMISSOR", "ESTADO_EMISSOR", "DATA_AUTORIZACAO", "CODIGO_AUTORIZACAO",
            "DATA_LICENCA", "NUMERO_LICENCA", "TIPO_LICENCA", "TIPO_CERTIFICADO", "NUMERO_CERTIFICADO", "DATA_VALIDADE_CERTIFICADO",
            "DATA_EMISSAO_CERTIFICADO", "RESULTADO_EXAME", "NOTA_EXAME", "TIPO_EXAME", "DATA_EXAME", "DATA_RESULTADO",
            "TIPO_CURSO", "NOME_CURSO", "CARGA_HORARIA_CURSO", "INSTITUICAO", "DATA_INICIO_CURSO", "DATA_FIM_CURSO",
            "RESULTADO_CURSO", "TIPO_PARTICIPANTE", "TIPO_AVALIACAO", "PONTUACAO", "PONTUACAO_MAXIMA", "PONTUACAO_MINIMA",
            "DATA_PONTUACAO", "BONIFICACAO", "RECOMPENSA", "DATA_RECOMPENSA", "MOTIVO_RECOMPENSA", "DATA_REEMBOLSO", "VALOR_REEMBOLSO",
            "TIPO_REEMBOLSO", "MOTIVO_REEMBOLSO", "DATA_DEVOLUCAO", "TIPO_DEVOLUCAO", "MOTIVO_REEMBOLSO", "DATA_RESERVA",
            "STATUS_RESERVA", "TIPO_RESERVA", "NUMERO_RESERVA", "DATA_CONFIRMACAO", "DATA_CANCELAMENTO", "MOTIVO_CANCELAMENTO",
            "DATA_ALTERACAO", "RESPONSAVEL_ALTERACAO", "LOG_ALTERACAO", "DATA_LOG", "TIPO_LOG", "DESCRICAO_LOG", "USUARIO_ALTERACAO",
            "USUARIO_APROVACAO", "USUARIO_REJEICAO", "USUARIO_SOLICITACAO", "DATA_SOLICITACAO", "TIPO_SOLICITACAO", "STATUS_SOLICITACAO",
            "OBSERVACAO", "ANOTACAO", "DESCRICAO_DETALHADA", "DATA_ANALISE", "RESULTADO_ANALISE", "DATA_TESTE", "RESULTADO_TESTE",
            "TIPO_TESTE", "FATOR", "INDICE", "TAXA", "PERCENTUAL", "PROBABILIDADE", "TENDENCIA", "DATA_PROJECAO", "RESULTADO_PROJECAO",
            "DATA_SIMULACAO", "RESULTADO_SIMULACAO", "PARAMETRO", "CONFIGURACAO", "VALOR_CONFIGURACAO", "VERSAO", "VERSAO_SOFTWARE",
            "VERSAO_ATUALIZACAO", "DATA_VERSAO", "LOG_ATUALIZACAO", "MOTIVO_ATUALIZACAO", "DATA_REINICIO", "STATUS_REINICIO",
            "CODIGO_ERRO", "MENSAGEM_ERRO", "DATA_ERRO", "SEVERIDADE_ERRO", "TIPO_ERRO", "ORIGEM_ERRO", "USUARIO_ERRO", "DATA_RECUPERACAO",
            "STATUS_RECUPERACAO", "TIPO_RECUPERACAO", "DATA_BACKUP", "TIPO_BACKUP", "TAMANHO_BACKUP", "LOCALIZACAO_BACKUP",
            "STATUS_BACKUP", "DATA_RESTAURACAO", "TIPO_RESTAURACAO", "STATUS_RESTAURACAO", "MOTIVO_RESTAURACAO", "TEMPO_RESTAURACAO",
            "DATA_MIGRACAO", "STATUS_MIGRACAO", "TIPO_MIGRACAO", "LOG_MIGRACAO", "DATA_SINCRONIZACAO", "STATUS_SINCRONIZACAO",
            "TIPO_SINCRONIZACAO", "TAMANHO_ARQUIVO_SINCRONIZADO", "USUARIO_SINCRONIZACAO", "DATA_ACAO", "TIPO_ACAO", "STATUS_ACAO",
            "LOG_ACAO", "USUARIO_ACAO", "RESULTADO_ACAO", "DATA_ANALISE_RISCO", "RESULTADO_RISCO", "TIPO_RISCO", "MITIGACAO_RISCO",
            "DATA_REVISAO", "STATUS_REVISAO", "USUARIO_REVISAO", "LOG_REVISAO", "TIPO_REVISAO", "DATA_INCIDENCIA", "FREQUENCIA_INCIDENCIA",
            "SEVERIDADE_INCIDENCIA", "DESCRICAO_INCIDENCIA", "DATA_CONTROLE", "TIPO_CONTROLE", "RESULTADO_CONTROLE", "USUARIO_CONTROLE",
            "LOG_CONTROLE", "DATA_METRICA", "VALOR_METRICA", "DESCRICAO_METRICA", "STATUS_METRICA", "TIPO_METRICA", "KPI", "VALOR_KPI",
            "DATA_KPI", "OBJETIVO_KPI", "RESULTADO_KPI", "DATA_OBJETIVO", "STATUS_OBJETIVO", "TIPO_OBJETIVO", "LOG_OBJETIVO",
            "USUARIO_OBJETIVO", "DATA_PLANEJAMENTO", "STATUS_PLANEJAMENTO", "TIPO_PLANEJAMENTO", "LOG_PLANEJAMENTO",
            "USUARIO_PLANEJAMENTO", "RESULTADO_PLANEJAMENTO", "MOTIVO_PLANEJAMENTO", "DATA_AGENDA", "STATUS_AGENDA",
            "TIPO_AGENDA", "LOG_AGENDA", "USUARIO_AGENDA"
    );

    private List<String> comments = List.of("Sistema em modo de manutenção", "Atualização de firmware em andamento",
            "Conexão segura estabelecida", "Dados criptografados com AES-256", "Erro de autenticação detectado",
            "Backup concluído com sucesso", "Falha na transmissão de dados", "Permissão de acesso negada",
            "Processo em execução no servidor", "Memória insuficiente disponível", "Configuração de rede aplicada",
            "Tempo de resposta otimizado", "Cache de DNS limpo", "Logs de erro exportados", "Disco rígido quase cheio",
            "Autenticação de dois fatores habilitada", "Certificado SSL expirado", "Reinicialização do sistema necessária",
            "Atualização de segurança aplicada", "Dados importados com sucesso", "Falha no processo de sincronização",
            "Arquivo corrompido detectado", "Controle de versão atualizado", "Servidor em alta carga", "Relatório de desempenho gerado",
            "Falha na integração com API", "Serviço de autenticação ativo", "Monitoramento de rede em tempo real",
            "Recurso de autoescala ativado", "Latência de rede reduzida", "Análise de logs concluída", "Componente depreciado em uso",
            "Verificação de integridade executada", "Permissões redefinidas com sucesso",
            "Tempo de inatividade agendado", "Consistência de banco de dados mantida", "Rotina de backup automatizada",
            "Espaço em disco monitorado", "Testes de carga realizados", "Atualização do software agendada", "Processo de depuração iniciado",
            "Falha de validação de entrada", "Conexão interrompida inesperadamente", "Requisição HTTP bem-sucedida", "Dados armazenados em cache",
            "Sistema de failover ativado", "Recurso não suportado", "Controle de acesso configurado", "Compilação bem-sucedida",
            "Rede privada virtual estabelecida", "Rotas de rede atualizadas", "Componentes críticos verificados",
            "Sistema rodando em modo seguro", "Reinicialização automática configurada", "Serviço de mensagens ativo",
            "Falha na autenticação de usuário", "Certificado digital válido", "Conformidade de segurança alcançada",
            "Ambiente de teste isolado", "Configuração de proxy aplicada", "Script executado sem erros",
            "Desempenho da CPU monitorado", "Índice de banco de dados otimizado", "Tabela temporária criada",
            "Conexão redundante configurada", "Licença de software verificada", "Permissões de leitura e escrita",
            "Processo assíncrono em execução", "Latência de E/S minimizada", "Upload de arquivo em andamento",
            "Checagem de dependências realizada", "Verificação de integridade de checksum", "Arquivo de configuração carregado",
            "Firewall configurado corretamente", "Sistema de alerta configurado", "Função recursiva detectada",
            "Assinatura digital verificada", "Serviço temporariamente indisponível", "Chave de API inválida",
            "Requisição excedeu o tempo limite", "Ambiente de produção ativo", "Permissões administrativas necessárias",
            "Testes de unidade concluídos", "Recompilação de projeto iniciada", "Utilização de memória alta",
            "Endpoint de API acessado", "Alocação dinâmica de memória", "Banco de dados sincronizado",
            "Patch de correção aplicado", "Cliente desconectado", "Análise de vulnerabilidades realizada",
            "Dispositivo configurado corretamente", "Tarefas agendadas executadas", "Script de inicialização carregado",
            "Erro de checksum detectado", "Backup diferencial completo", "Monitoramento de tráfego ativado",
            "Permissões restritas aplicadas", "Registro de transação confirmado", "Conexão de banco de dados perdida",
            "Falha no protocolo de comunicação", "Thread de execução terminada"
    );

    public static List<DatabaseInputDTO> generateDatabase() {
        List<DatabaseInputDTO> databases = new ArrayList<>();

        dataBaseNames.forEach(databaseName -> {

            List<TableInputDTO> tableInputDTOList = new ArrayList<>();
            int tablesQtd = getRandomNumberUsingNextInt(3, 30);
            for (int i = 0; i < tablesQtd; i++) {
                tableInputDTOList.add(generateTable());
            }

            databases.add(DatabaseInputDTO.builder()
                    .name(databaseName)
                    .indTechnology(randomTechnology())
                    .databaseId(UUID.randomUUID().toString())
                    .containerId(UUID.randomUUID().toString())
                    .hostName(getIp())
                    .environment(randomEnv())
                    .status("ACTIVE")
                    .tables(tableInputDTOList)
                    .build()
            );
        });

        return databases;
    }

    public static TableInputDTO generateTable() {

        List<ColumnInputDTO> columnInputDTOList = new ArrayList<>();
        int tablesQtd = getRandomNumberUsingNextInt(11, 101);
        for (int i = 0; i < tablesQtd; i++) {
            columnInputDTOList.add(generateColumn());
        }

        return TableInputDTO.builder()
                .schema(randomDataBaseName().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .name(randomTableName().toUpperCase() + "_" + getRandomNumberUsingNextInt(0, 999999))
                .comment(randomComments())
                .status("ACTIVE")
                .columns(columnInputDTOList)
                .build();
    }

    public static ColumnInputDTO generateColumn() {
        return ColumnInputDTO.builder()
                .name(randomColumnName())
                .comment(randomComments())
                .status("ACTIVE")
                .build();
    }

    public static String randomEnv() {
        List<String> env = Arrays.asList("PROD", "DEV", "QA");
        SecureRandom rand = new SecureRandom();
        return env.get(rand.nextInt(env.size()));
    }

    public static String randomTechnology() {
        List<String> technology = Arrays.asList("ORACLE", "MYSQL", "REDSHIFT", "MARIADB");
        SecureRandom rand = new SecureRandom();
        return technology.get(rand.nextInt(technology.size()));
    }

    public static String randomDataBaseName() {
        SecureRandom rand = new SecureRandom();
        return dataBaseNames.get(rand.nextInt(dataBaseNames.size()));
    }

    public static String randomTableName() {
        SecureRandom rand = new SecureRandom();
        return tablesNames.get(rand.nextInt(tablesNames.size()));
    }

    public static String randomColumnName() {
        SecureRandom rand = new SecureRandom();
        return tablesNames.get(rand.nextInt(columnNames.size()));
    }

    public static String randomComments() {
        SecureRandom rand = new SecureRandom();
        return tablesNames.get(rand.nextInt(comments.size()));
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max - min) + min;
    }

    public static String getIp() {
        return getRandomNumberUsingNextInt(1, 999) + "." + getRandomNumberUsingNextInt(1, 999) + "." +
                getRandomNumberUsingNextInt(1, 999) + "." + getRandomNumberUsingNextInt(1, 999);
    }
}
