/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.seata.core.model;

import org.apache.seata.core.exception.TransactionException;

/**
 * Transaction Manager.
 * 事务管理器
 * Define a global transaction and control it.
 *
 */
public interface TransactionManager {

    /**
     * Begin a new global transaction.
     *
     * @param applicationId           ID of the application who begins this transaction.
     * @param transactionServiceGroup ID of the transaction service group.
     * @param name                    Give a name to the global transaction.
     * @param timeout                 Timeout of the global transaction.
     * @return XID of the global transaction
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    String begin(String applicationId, String transactionServiceGroup, String name, int timeout)
        throws TransactionException;

    /**
     * Global commit.
     *
     * @param xid XID of the global transaction.
     * @return Status of the global transaction after committing.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    GlobalStatus commit(String xid) throws TransactionException;

    /**
     * Global rollback.
     *
     * @param xid XID of the global transaction
     * @return Status of the global transaction after rollbacking.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    GlobalStatus rollback(String xid) throws TransactionException;

    /**
     * Get current status of the give transaction.
     *
     * @param xid XID of the global transaction.
     * @return Current status of the global transaction.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    GlobalStatus getStatus(String xid) throws TransactionException;

    /**
     * Global report.
     *
     * @param xid XID of the global transaction.
     * @param globalStatus Status of the global transaction.
     * @return Status of the global transaction.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    GlobalStatus globalReport(String xid, GlobalStatus globalStatus) throws TransactionException;
}
