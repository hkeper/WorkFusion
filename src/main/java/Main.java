import business.service.UserService;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import peristance.IUserRepository;
import peristance.InMemoryUserRepository;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public class Main {

    private static IUserRepository repository = new InMemoryUserRepository();
    private static UserService service = new UserService();

    public static void main(String[] args) {
        service.setUserRepository(repository);

        staticFiles.location("/public");
        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("status", "N/A");
            model.put("users", service.getUserInfoList());
            return new FreeMarkerEngine().render(new ModelAndView(model, "index.ftl"));
        });
        post("/index", (req, res) -> {
            MultiMap<String> params = new MultiMap<>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");

            Map<String, Object> model = new HashMap<>();
            model.put("status",
                    service.addUser(
                            params.getString("username"),
                            params.getString("name"),
                            params.getString("password")
                            )
            );
            model.put("users", service.getUserInfoList());
            return new FreeMarkerEngine().render(new ModelAndView(model, "index.ftl"));
        });
    }

}
