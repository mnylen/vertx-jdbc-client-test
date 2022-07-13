Reproduction of parameter metadata issue with Vert.x 4.x JDBC client.

Prerequisite: start mysql server using docker-compose:

```
docker-compose up -d
```

In master branch, the following fails, whereas in vertx-3 branch it succeeds:

```
./gradlew run
```

The error in master is:

```
java.lang.RuntimeException: java.sql.SQLException: Parameter metadata not available for the given statement
        at io.reactivex.internal.util.ExceptionHelper.wrapOrThrow(ExceptionHelper.java:46)
        at io.reactivex.internal.observers.BlockingMultiObserver.blockingGet(BlockingMultiObserver.java:93)
        at io.reactivex.Completable.blockingAwait(Completable.java:1227)
        at vertx.JdbcClientTest.main(JdbcClientTest.java:41)
Caused by: java.sql.SQLException: Parameter metadata not available for the given statement
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
        at com.mysql.cj.jdbc.MysqlParameterMetadata.checkAvailable(MysqlParameterMetadata.java:86)
        at com.mysql.cj.jdbc.MysqlParameterMetadata.getParameterType(MysqlParameterMetadata.java:138)
        at io.vertx.ext.jdbc.impl.actions.CachedParameterMetaData$QueryMeta.getParameterType(CachedParameterMetaData.java:78)
        at io.vertx.ext.jdbc.impl.actions.CachedParameterMetaData.getParameterType(CachedParameterMetaData.java:174)
        at io.vertx.ext.jdbc.spi.JDBCColumnDescriptorProvider.lambda$null$6(JDBCColumnDescriptorProvider.java:61)
        at io.vertx.jdbcclient.impl.actions.JDBCPropertyAccessor.lambda$create$0(JDBCPropertyAccessor.java:39)
        at io.vertx.jdbcclient.impl.actions.JDBCColumnDescriptor.create(JDBCColumnDescriptor.java:72)
        at io.vertx.ext.jdbc.spi.JDBCColumnDescriptorProvider.lambda$fromParameterMetaData$9(JDBCColumnDescriptorProvider.java:60)
        at io.vertx.ext.jdbc.spi.impl.JDBCEncoderImpl.encode(JDBCEncoderImpl.java:45)
        at io.vertx.ext.jdbc.impl.actions.AbstractJDBCAction.fillStatement(AbstractJDBCAction.java:106)
        at io.vertx.ext.jdbc.impl.actions.AbstractJDBCAction.fillStatement(AbstractJDBCAction.java:97)
        at io.vertx.ext.jdbc.impl.actions.JDBCUpdate.execute(JDBCUpdate.java:89)
        at io.vertx.ext.jdbc.impl.actions.JDBCUpdate.execute(JDBCUpdate.java:30)
        at io.vertx.ext.jdbc.impl.JDBCConnectionImpl.lambda$schedule$3(JDBCConnectionImpl.java:219)
        at io.vertx.core.impl.ContextBase.lambda$null$0(ContextBase.java:137)
        at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:264)
        at io.vertx.core.impl.ContextBase.lambda$executeBlocking$1(ContextBase.java:135)
        at io.vertx.core.impl.TaskQueue.run(TaskQueue.java:76)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:829)
```