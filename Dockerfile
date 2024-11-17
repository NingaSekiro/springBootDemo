FROM adoptopenjdk/openjdk8
WORKDIR /
ADD target/springboot-demo-helloworld.jar springboot-demo-helloworld.jar
EXPOSE 8080
LABEL version="1.0"
#ENTRYPOINT ["java", "-jar"]
#y 表示同意安装
RUN  apt-get update && apt-get install -y \
#     pmap
procps \
busybox \
gdb \
#     字节工具 strings
binutils
CMD java -XX:NativeMemoryTracking=detail -XX:MaxDirectMemorySize=128m -XX:+PrintGCDetails -XX:NativeMemoryTracking=summary -jar springboot-demo-helloworld.jar
