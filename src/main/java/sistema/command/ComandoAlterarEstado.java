package sistema.command;
import sistema.state.ContextoEstado;
import sistema.state.Estado;
import sistema.utils.LoggerSistema;
public class ComandoAlterarEstado implements Comando {
    private final ContextoEstado contexto;
    private final Estado novoEstado;
    private final Estado estadoAnterior;
    private final LoggerSistema logger;
    public ComandoAlterarEstado(ContextoEstado contexto, Estado novoEstado) {
        this.contexto = contexto;
        this.novoEstado = novoEstado;
        this.estadoAnterior = contexto.getEstadoAtual();
        this.logger = LoggerSistema.getInstance();
    }
    @Override
    public boolean executar() {
        try {
            if (estadoAnterior.podeTransicionarPara(novoEstado)) {
                contexto.alterarEstado(novoEstado);
                logger.logar("ALTERACAO_ESTADO",
                    contexto.getNomeParaLog() + " alterou estado de " +
                    estadoAnterior.getNome() + " para " + novoEstado.getNome());
                return true;
            } else {
                logger.logar("ERRO_ESTADO",
                    "Transição de estado inválida: " + estadoAnterior.getNome() +
                    " -> " + novoEstado.getNome() + " para " + contexto.getNomeParaLog());
                return false;
            }
        } catch (Exception e) {
            logger.logar("ERRO_ESTADO",
                "Erro ao alterar estado: " + e.getMessage() + " para " + contexto.getNomeParaLog());
            return false;
        }
    }
    @Override
    public void desfazer() {
        try {
            contexto.alterarEstado(estadoAnterior);
            logger.logar("REVERSAO_ESTADO",
                contexto.getNomeParaLog() + " reverteu estado de " +
                novoEstado.getNome() + " para " + estadoAnterior.getNome());
        } catch (Exception e) {
            logger.logar("ERRO_ESTADO",
                "Erro ao reverter estado: " + e.getMessage() + " para " + contexto.getNomeParaLog());
        }
    }
}
