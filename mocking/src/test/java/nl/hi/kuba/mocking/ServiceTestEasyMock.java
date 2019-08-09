package nl.hi.kuba.mocking;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Service.class)
public class ServiceTestEasyMock {
    @Test
    public void testFinalInBase() throws Exception {
        String finalMethod = "publicFinalMethod";
        Service powerMock = PowerMock.createPartialMock(Service.class, finalMethod);

        PowerMock.expectPrivate(powerMock, finalMethod).andReturn(new String[]{"public final mocked..."});
        PowerMock.replay(powerMock);

        System.out.println("finalMethod(PM)=" + powerMock.publicFinalMethod());
    }

    @Test
    public void testFinalPublicAndVoidByPowerMock() throws Exception {
        String finalMethod = "finalMethod";
        Service powerMock = PowerMock.createPartialMock(Service.class, finalMethod, "publicMethod", "voidMethod");

        PowerMock.expectPrivate(powerMock, finalMethod).andReturn("final mocked...");
        PowerMock.expectPrivate(powerMock, "publicMethod").andReturn("public mocked...");
        powerMock.voidMethod();
        PowerMock.expectLastCall();
        PowerMock.replay(powerMock);

        System.out.println("voidMethod(PM)=");
        powerMock.voidMethod();
        System.out.println("publicMethod(PM)=" + powerMock.publicMethod());
        System.out.println("finalMethod(PM)=" + powerMock.finalMethod());
    }

    @Test
    public void testFinalAndPublicByPowerMock() throws Exception {
        String finalMethod = "finalMethod";
        Service powerMock = PowerMock.createPartialMock(Service.class, finalMethod, "publicMethod");

        PowerMock.expectPrivate(powerMock, finalMethod).andReturn("final mocked...");
        PowerMock.expectPrivate(powerMock, "publicMethod").andReturn("public mocked...");
        PowerMock.replay(powerMock);

        System.out.println("publicMethod(PM)=" + powerMock.publicMethod());
        System.out.println("finalMethod(PM)=" + powerMock.finalMethod());
    }

    @Test
    public void testFinalAndOthersByPowerMock() throws Exception { // no last call on a mock available
        String finalMethod = "finalMethod";
        //Service service = PowerMock.createPartialMock(Service.class, finalMethod);

        Service easyMock = EasyMock.createMock(Service.class);
        EasyMock.expect(easyMock.publicMethod()).andReturn("Mocked");
        EasyMock.replay(easyMock);

        PowerMock.expectNew(Service.class).andReturn(easyMock);
        Service service = new Service();
        PowerMock.expectPrivate(service, finalMethod).andReturn("Mocked");
        PowerMock.replay(easyMock);
        PowerMock.replay(Service.class);

        System.out.println("finalMethod(PM)=" + easyMock.finalMethod());
        System.out.println("publicMethod(PM)=" + service.publicMethod());
    }

    @Test
    public void testFinalByPowerMock() throws Exception {
        String finalMethod = "finalMethod";
        Service service = PowerMock.createPartialMock(Service.class, finalMethod);
        PowerMock.expectPrivate(service, finalMethod).andReturn("Mocked");

        PowerMock.replay(service);
        System.out.println("finalMethod(PM)=" + service.finalMethod());
    }

    @Test
    public void testStaticByPowerMock() {
        try {
            PowerMock.mockStaticNice(Service.class);
            EasyMock.expect(Service.staticMethod()).andReturn("Mocked...");
            PowerMock.replayAll();

            System.out.println("getString(PM)=" + Service.staticMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinalByEasyMock() { // exception: no last call on a mock available
        try {
            Service easyMock = EasyMock.createMock(Service.class);
            EasyMock.expect(easyMock.finalMethod()).andReturn("Mocked").anyTimes();
            EasyMock.replay(easyMock);

            System.out.println("finalMethod(EM)=" + easyMock.finalMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPublicByEasyMock() {
        try {
            Service easyMock = EasyMock.createMock(Service.class);
            EasyMock.expect(easyMock.publicMethod()).andReturn("Mocked").anyTimes();
            EasyMock.replay(easyMock);

            System.out.println("publicMethod(EM)=" + easyMock.publicMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}