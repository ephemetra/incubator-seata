/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.tm.api;

import io.seata.core.exception.TransactionException;
import io.seata.core.model.GlobalStatus;
import io.seata.tm.api.transaction.SuspendedResourcesHolder;

/**
 * Global transaction.
 * 全局事务：包括开启事务、提交、回滚、获取当前状态等方法。
 * @author sharajava
 */
public interface GlobalTransaction {

    /**
     * 使用默认超时和名称开始一个新的全局事务。
     *
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void begin() throws TransactionException;

    /**
     * 使用给定超时和默认名称开始新的全局事务。
     *
     * @param timeout Global transaction timeout in MILLISECONDS
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void begin(int timeout) throws TransactionException;

    /**
     * 使用给定超时和给定名称开始一个新的全局事务。
     *
     * @param timeout Given timeout in MILLISECONDS.
     * @param name    Given name.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void begin(int timeout, String name) throws TransactionException;

    /**
     * 提交全局事务。
     *
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void commit() throws TransactionException;

    /**
     * 回滚全局事务。
     *
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void rollback() throws TransactionException;

    /**
     * 挂起全局事务。
     *
     * @return the SuspendedResourcesHolder which holds the suspend resources
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * @see SuspendedResourcesHolder
     */
    SuspendedResourcesHolder suspend() throws TransactionException;

    /**
     * 挂起全局事务。
     *
     * @param clean 如果为true，则清空事务上下文。否则，只挂起
     * @return the SuspendedResourcesHolder which holds the suspend resources
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * @see SuspendedResourcesHolder
     */
    SuspendedResourcesHolder suspend(boolean clean) throws TransactionException;

    /**
     * 恢复全局事务。
     *
     * @param suspendedResourcesHolder 将被恢复的挂起资源上下文
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     * @see SuspendedResourcesHolder
     */
    void resume(SuspendedResourcesHolder suspendedResourcesHolder) throws TransactionException;

    /**
     * 向TC询问相应全局事务的当前状态。
     *
     * @return Status of the corresponding global transaction.
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     * @see GlobalStatus
     */
    GlobalStatus getStatus() throws TransactionException;

    /**
     * 获取 XID.
     *
     * @return XID. xid
     */
    String getXid();

    /**
     * 报告全局事务状态。
     *
     * @param globalStatus global status.
     *
     * @throws TransactionException Any exception that fails this will be wrapped with TransactionException and thrown
     * out.
     */
    void globalReport(GlobalStatus globalStatus) throws TransactionException;

    /**
     * 获取全局事务的本地状态。
     *
     * @return Status of the corresponding global transaction.
     * @see GlobalStatus
     */
    GlobalStatus getLocalStatus();

    /**
     * 获取全局事务角色。
     *
     * @return global transaction Role.
     * @see GlobalTransactionRole
     */
    GlobalTransactionRole getGlobalTransactionRole();

    /**
     * 获取创建时间
     *
     * @return create time
     */
    long getCreateTime();
}
