package vertx;

import io.reactivex.Completable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.reactivex.ext.sql.SQLClient;

import java.time.Instant;
import java.util.UUID;

public class JdbcClientTest {
    public static void main(final String[] args) {
        final Vertx vertx = Vertx.vertx();
        final SQLClient client = JDBCClient.createShared(vertx, new JsonObject()
                .put("driver_class", "com.mysql.cj.jdbc.Driver")
                .put("url", "jdbc:mysql://localhost:23306/test")
                .put("user", "test")
                .put("password", "test")
        );

        final String sql = "INSERT INTO test (str, created) VALUES (?, ?)";
        final JsonArray params = new JsonArray()
                .add(UUID.randomUUID().toString())
                        .add(Instant.now());

        try {
            client.rxGetConnection()
                    .flatMapCompletable(conn -> conn.rxSetAutoCommit(false)
                            .andThen(
                                    conn
                                            .rxUpdateWithParams(sql, params)
                                            .flatMapCompletable(res ->
                                                    res.getUpdated() == 1
                                                            ? Completable.complete()
                                                            : Completable.error(new RuntimeException("No row was inserted"))
                                            )
                            )
                            .andThen(conn.rxCommit()))
                    .blockingAwait();

            System.out.println("Successfully inserted a row");
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            vertx.rxClose().blockingAwait();
            System.exit(0);
        }
    }
}
