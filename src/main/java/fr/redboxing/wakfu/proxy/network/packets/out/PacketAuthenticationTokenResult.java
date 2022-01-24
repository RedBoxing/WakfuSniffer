package fr.redboxing.wakfu.proxy.network.packets.out;

import fr.redboxing.wakfu.proxy.WakfuProxy;
import fr.redboxing.wakfu.proxy.network.packets.Packet;
import fr.redboxing.wakfu.proxy.session.ClientSession;
import fr.redboxing.wakfu.proxy.utils.DataUtils;
import io.netty.buffer.ByteBuf;

public class PacketAuthenticationTokenResult extends Packet {
    PacketAuthenticationResult.LoginResponseCode code;
    String token;

    public PacketAuthenticationTokenResult(ByteBuf packet, PacketType packetType, ClientSession session, boolean know) {
        super(packet, packetType, session, know);
    }

    @Override
    public ByteBuf decode() {
        code = PacketAuthenticationResult.LoginResponseCode.getByID(packet.readByte());
        token = DataUtils.readLargeString(packet);

        WakfuProxy.getInstance().getLogger().info(getClass().getSimpleName() + " : " + toString());

        return super.decode();
    }

    @Override
    public String toString() {
        return "{ code: " + code + ", token: " + token + " }";
    }
}
