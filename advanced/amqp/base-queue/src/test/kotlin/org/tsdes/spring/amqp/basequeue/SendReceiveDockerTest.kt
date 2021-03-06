package org.tsdes.spring.amqp.basequeue

import org.junit.Test
import org.junit.Assert.*
import org.testcontainers.containers.GenericContainer
import org.junit.ClassRule
import org.testcontainers.containers.FixedHostPortGenericContainer
import org.springframework.boot.test.util.EnvironmentTestUtils
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.ApplicationContextInitializer




/**
 * Created by arcuri82 on 07-Aug-17.
 */
class SendReceiveDockerTest{

    /*
        If we you are using Windows, make sure that under the "General" configurations
        of Docker you enable:
        "Expose daemon on tcp://localhost:2375 without TLS"
     */

    companion object {

        /*
            workaround to current Kotlin (and other JVM languages) limitation
            see https://github.com/testcontainers/testcontainers-java/issues/318
         */
        class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)

        /*
            This will start a RabbitMQ server using Docker.

            This is "similar" to start the following from command-line:

            docker run -p 5672:5672 rabbitmq:3

            However, here, although the port is exposed, it is mapped to a
            random, free one.
         */
        @ClassRule @JvmField
        val rabbitMQ = KGenericContainer("rabbitmq:3")
                .withExposedPorts(5672)
    }

    @Test
    fun receiveNull(){

        val msg = Receiver(rabbitMQ.containerIpAddress, rabbitMQ.getMappedPort(5672))
                .receive("queue:receiveNull")
        assertNull(msg)
    }


    @Test
    fun testSendAndReceive(){

        val msg = "Hello World!"
        val queueName = "queue:testSendAndReceive"

        Sender(rabbitMQ.containerIpAddress, rabbitMQ.getMappedPort(5672))
                .send(queueName, msg)

        val res = Receiver(rabbitMQ.containerIpAddress, rabbitMQ.getMappedPort(5672))
                .receive(queueName)

        assertEquals(msg, res)
    }
}