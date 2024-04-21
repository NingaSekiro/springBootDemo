//package com.example.springdemo.demos.web.stateMachine.config;
//
//import org.springframework.statemachine.StateMachine;
//import org.springframework.stereotype.Component;
//
//import static org.aspectj.weaver.Shadow.ExceptionHandler;
//
//@Component
//public class PaymentExceptionHandler extends ExceptionHandler<Object, String, Object> {
//
//    @Override
//    public boolean handleException(StateMachineExceptionContext<Object, String, Object> context) {
//        // 获取抛出的异常
//        Exception exception = context.getException();
//
//        // 如果是支付失败异常
//        if (exception instanceof PaymentFailedException) {
//            // 获取当前状态机
//            StateMachine<String, String> stateMachine = context.getStateMachine();
//
//            // 获取当前状态
//            String currentState = stateMachine.getState().getId();
//
//            // 如果当前状态已经是WAITING_FOR_RECEIVE，说明状态转换已经发生
//            if ("WAITING_FOR_RECEIVE".equals(currentState)) {
//                // 这里可以采取措施，如记录日志、通知用户等
//
//                // 如果希望回滚状态，可以尝试手动触发状态转换
//                stateMachine.sendEvent("rollback_to_UNPAID");
//            }
//
//            // 返回true表示已处理此异常，状态机将不会传播此异常
//            return true;
//        }
//
//        // 对于其他类型的异常，可以选择不处理或按需处理
//        return super.handleException(context);
//    }
//}
