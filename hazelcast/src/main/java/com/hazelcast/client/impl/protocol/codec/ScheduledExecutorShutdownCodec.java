/*
 * Copyright (c) 2008-2023, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
 * Invocation has no additional effect if already shut down.
 */
@SuppressWarnings("unused")
@Generated("a1b8fc550c4add316c820df90b5becda")
public final class ScheduledExecutorShutdownCodec {
    //hex: 0x1A0100
    public static final int REQUEST_MESSAGE_TYPE = 1704192;
    //hex: 0x1A0101
    public static final int RESPONSE_MESSAGE_TYPE = 1704193;
    private static final int REQUEST_MEMBER_UUID_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_FRAME_SIZE = REQUEST_MEMBER_UUID_FIELD_OFFSET + UUID_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;

    private ScheduledExecutorShutdownCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * Name of the scheduler.
         */
        public java.lang.String schedulerName;

        /**
         * The UUID of the member where the shutdown for this scheduler will be sent.
         */
        public java.util.UUID memberUuid;
    }

    public static ClientMessage encodeRequest(java.lang.String schedulerName, java.util.UUID memberUuid) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(false);
        clientMessage.setOperationName("ScheduledExecutor.Shutdown");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        encodeUUID(initialFrame.content, REQUEST_MEMBER_UUID_FIELD_OFFSET, memberUuid);
        clientMessage.add(initialFrame);
        StringCodec.encode(clientMessage, schedulerName);
        return clientMessage;
    }

    public static ScheduledExecutorShutdownCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        request.memberUuid = decodeUUID(initialFrame.content, REQUEST_MEMBER_UUID_FIELD_OFFSET);
        request.schedulerName = StringCodec.decode(iterator);
        return request;
    }

    public static ClientMessage encodeResponse() {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        clientMessage.add(initialFrame);

        return clientMessage;
    }
}
