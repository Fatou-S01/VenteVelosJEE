package sn.ept.git.dic2.vente_velo.ServiceWeb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "API for Bicycle Shop",
                description = "Ceci permet de faire la documentation de notre api",
                contact = @Contact(
                        name = "Fatou",
                        email = "sfatou@ept.sn",
                        url = "https://github.com/swagger-api/swagger-ui"
                ),
                version = "1.0.0",
                license = @License(name = "OpenSource")
        ),
        servers = {@Server(
                url = "{urlDeploiement}",
                variables = {@ServerVariable(
                        name = "urlDeploiement",
                        defaultValue = "http://localhost:8080/Vente_velo-1.0-SNAPSHOT/"
                )}
        )}

)
public class ApiRestConfig extends Application {
}
