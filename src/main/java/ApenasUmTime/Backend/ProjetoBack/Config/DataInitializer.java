package ApenasUmTime.Backend.ProjetoBack.Config;

import ApenasUmTime.Backend.ProjetoBack.autenticacao.Usuario;
import ApenasUmTime.Backend.ProjetoBack.autenticacao.UsuarioService;
import ApenasUmTime.Backend.ProjetoBack.autenticacao.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import org.mindrot.jbcrypt.BCrypt; // 🔹 Adicione este import

@Component
@Order(0)
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Value("${app.bootstrap.admin.email:usuario@email.com}")
    private String adminEmail;

    @Value("${app.bootstrap.admin.password:z1x2c3v4}")
    private String adminPassword;

    public DataInitializer(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            if (usuarioRepository.findByEmail(adminEmail) == null) {

                Usuario admin = new Usuario(adminEmail, adminPassword);
                usuarioService.cadastrarUsuario(admin);
                log.info("Usuário inicial criado: {}", adminEmail);
            } else {
                log.info("Usuário inicial já existe: {}", adminEmail);
            }
        } catch (ResponseStatusException ex) {
            log.warn("Não foi possível criar o usuário inicial: {} - {}", adminEmail, ex.getReason());
        } catch (Exception e) {
            log.error("Erro ao inicializar usuário padrão", e);
        }
    }
}
