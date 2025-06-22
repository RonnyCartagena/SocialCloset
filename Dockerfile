# Dockerfile para desplegar paquete WAR ya generado
# Use a supported JDK base (e.g. JDK17 LTS)
FROM tomcat:11.0.6-jdk17
ENV CATALINA_HOME /usr/local/tomcat
WORKDIR $CATALINA_HOME

# Limpiar aplicaciones por defecto
RUN rm -rf $CATALINA_HOME/webapps/*

# Copiar WAR ya compilado desde el contexto de construcción
# Asegúrate de que "target/socialCloset-1.0-SNAPSHOT.war" exista antes de build
COPY target/socialCloset-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war

# Exponer puerto de Tomcat
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]