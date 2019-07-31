package accuratemoney;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 金额计算器
 *
 * @author lijing
 */
public class MoneyHelper {

    public static void main(String[] args) {
        //投资人1回款计划
        List<Plan> investIncomeList = calculateInvestPlan(new BigDecimal("10000"), new BigDecimal("10"), 12);
        BigDecimal totalInterest = BigDecimal.ZERO;
        for (Plan investIncome : investIncomeList) {
            totalInterest = totalInterest.add(investIncome.getInterest());
            System.out.println(investIncome.getPeriod() + ": " + investIncome);
        }
        System.out.println("total: " + totalInterest.toString());
        System.out.println();

        //投资人2回款计划
        List<Plan> investIncomeList2 = calculateInvestPlan(new BigDecimal("23000"), new BigDecimal("10"), 12);
        BigDecimal totalInterest2 = BigDecimal.ZERO;
        for (Plan investIncome : investIncomeList2) {
            totalInterest2 = totalInterest2.add(investIncome.getInterest());
            System.out.println(investIncome.getPeriod() + ": " + investIncome);
        }
        System.out.println("total: " + totalInterest2.toString());
        System.out.println();
        BigDecimal feeA = new BigDecimal("2");
        BigDecimal feeB = new BigDecimal("3");
        BigDecimal totalServiceFeeRate = feeA.add(feeB);
        List<Plan> repayPlanList = new ArrayList<>(investIncomeList2.size());
        BigDecimal remainPrincipal = new BigDecimal("33000");
        BigDecimal totalInterest3 = BigDecimal.ZERO;
        BigDecimal repayMonthTotal = calculateMonthTotal(remainPrincipal, new BigDecimal("15"), 12);
        for (int index = 0; index < investIncomeList2.size(); index++) {
            Plan repayPlan = new Plan();
            repayPlan.setTotal(repayMonthTotal);
            repayPlan.setPeriod(index + 1);
            repayPlan.setInterest(investIncomeList.get(index).getInterest().add(investIncomeList2.get(index).getInterest()));
            repayPlan.setPrincipal(investIncomeList.get(index).getPrincipal().add(investIncomeList2.get(index).getPrincipal()));
            remainPrincipal = remainPrincipal.subtract(repayPlan.getPrincipal());
            repayPlan.setOutstanding(remainPrincipal);
            BigDecimal totalServiceFee = repayMonthTotal.subtract(repayPlan.getInterest().add(repayPlan.getPrincipal())).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal manageFeeA = feeA.divide(totalServiceFeeRate, 2, BigDecimal.ROUND_HALF_UP).multiply(totalServiceFee).setScale(2, BigDecimal.ROUND_DOWN);
            repayPlan.setManagerFeeA(manageFeeA);
            totalServiceFee = totalServiceFee.subtract(manageFeeA);
            repayPlan.setManagerFeeB(totalServiceFee);
            repayPlanList.add(repayPlan);
        }

        for (Plan investIncome : repayPlanList) {
            totalInterest3 = totalInterest3.add(investIncome.getInterest());
            System.out.println(investIncome.getPeriod() + ": " + investIncome);
        }
        System.out.println("total: " + totalInterest3.toString());
        System.out.println();
    }

    /**
     * 计算借款人还款计划
     *
     * @param amount 借款金额
     * @param period 总期数
     * @return
     */
    public static List<Plan> calculateRepayPlan(BigDecimal amount, int period) {
        //借款人还款计划
        BigDecimal remainPrincipal = amount;
        List<Plan> repayPlanList = new ArrayList<>(period);
        for (int index = 0; index < period; index++) {
            Plan totalSum = calculateSumInvestPlan(index + 1);
            Plan repayPlan = new Plan();
            repayPlan.setInterest(totalSum.getInterest());
            repayPlan.setPrincipal(totalSum.getPrincipal());
            remainPrincipal = remainPrincipal.subtract(repayPlan.getPrincipal());
            repayPlan.setOutstanding(remainPrincipal);
            repayPlanList.add(repayPlan);
        }
        return repayPlanList;
    }

    /**
     * 计算借款人还款计划，有服务费
     *
     * @param amount 借款金额
     * @param period 总期数
     * @return
     */
    public static List<Plan> calculateRepayPlanWithFee(BigDecimal amount, BigDecimal rate, int period, BigDecimal feeA, BigDecimal feeB) {
        //借款人还款计划
        BigDecimal remainPrincipal = amount;
        List<Plan> repayPlanList = new ArrayList<>(period);
        BigDecimal monthRepay = calculateMonthTotal(amount, rate, period);
        BigDecimal totalServiceFeeRate = feeA.add(feeB);
        for (int index = 0; index < period; index++) {
            Plan totalSum = calculateSumInvestPlan(index + 1);
            Plan repayPlan = new Plan();
            repayPlan.setInterest(totalSum.getInterest());
            repayPlan.setPrincipal(totalSum.getPrincipal());
            remainPrincipal = remainPrincipal.subtract(repayPlan.getPrincipal());
            repayPlan.setOutstanding(remainPrincipal);
            BigDecimal totalServiceFee = monthRepay.subtract(totalSum.getTotal()).setScale(2, BigDecimal.ROUND_DOWN);
            //存在手续费，各手续费按利率的比例分配
            BigDecimal manageFeeA = feeA.divide(totalServiceFeeRate, 2, BigDecimal.ROUND_HALF_UP).multiply(totalServiceFee).setScale(2, BigDecimal.ROUND_DOWN);
            repayPlan.setManagerFeeA(manageFeeA);
            totalServiceFee = totalServiceFee.subtract(manageFeeA);
            repayPlan.setManagerFeeB(totalServiceFee);
            repayPlanList.add(repayPlan);
        }
        return repayPlanList;
    }

    public static Plan calculateSumInvestPlan(int period) {
        //模拟统计第period期汇总
        return new Plan();
    }

    /**
     * 等额本息，计算每月应收或者应还总额
     *
     * @param amount 金额基数
     * @param rate   利率
     * @param period 总月数
     */
    public static BigDecimal calculateMonthTotal(BigDecimal amount, BigDecimal rate, int period) {
        //月利率
        double monthRate = rate.doubleValue() / 100 / 12;
        //每月回款总额，等于本金 + 利息
        return amount.multiply(new BigDecimal(monthRate * Math.pow(1 + monthRate, period)))
                .divide(new BigDecimal(Math.pow(1 + monthRate, period) - 1), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static List<Plan> calculateInvestPlan(BigDecimal investAmount, BigDecimal rate, int period) {
        //月利率
        double monthRate = rate.doubleValue() / 100 / 12;
        //每月回款总额，等于本金 + 利息
        BigDecimal monthIncome = calculateMonthTotal(investAmount, rate, period);
        BigDecimal monthInterest;
        BigDecimal remainPrincipal = investAmount;
        List<Plan> investIncomeList = new ArrayList<>(period);
        for (int i = 1; i <= period; i++) {
            BigDecimal multiply = investAmount.multiply(new BigDecimal(monthRate));
            BigDecimal sub = new BigDecimal(Math.pow(1 + monthRate, period)).subtract(new BigDecimal(Math.pow(1 + monthRate, i - 1)));
            //每月利息
            monthInterest = multiply.multiply(sub).divide(new BigDecimal(Math.pow(1 + monthRate, period) - 1), 2, BigDecimal.ROUND_HALF_UP);
            Plan investIncome = new Plan();
            investIncome.setTotal(monthIncome);
            investIncome.setPeriod(i);
            if (i < period) {
                //不是最后一期
                investIncome.setPrincipal(monthIncome.subtract(monthInterest));
                investIncome.setInterest(monthInterest);
            } else {
                //最后一期做差值
                investIncome.setPrincipal(remainPrincipal);
                investIncome.setInterest(monthIncome.subtract(investIncome.getPrincipal()));
            }
            remainPrincipal = remainPrincipal.subtract(investIncome.getPrincipal());
            investIncome.setOutstanding(remainPrincipal);
            investIncomeList.add(investIncome);
        }
        return investIncomeList;
    }

    public static class Plan {
        //当前期数
        private int period;
        //当期本金
        private BigDecimal principal;
        //当期利息
        private BigDecimal interest;
        //剩余本金
        private BigDecimal outstanding;
        //应还总额
        private BigDecimal total;
        private BigDecimal managerFeeA;
        private BigDecimal managerFeeB;

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public BigDecimal getPrincipal() {
            return principal;
        }

        public void setPrincipal(BigDecimal principal) {
            this.principal = principal;
        }

        public BigDecimal getInterest() {
            return interest;
        }

        public void setInterest(BigDecimal interest) {
            this.interest = interest;
        }

        public BigDecimal getOutstanding() {
            return outstanding;
        }

        public void setOutstanding(BigDecimal outstanding) {
            this.outstanding = outstanding;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getManagerFeeA() {
            return managerFeeA;
        }

        public void setManagerFeeA(BigDecimal managerFeeA) {
            this.managerFeeA = managerFeeA;
        }

        public BigDecimal getManagerFeeB() {
            return managerFeeB;
        }

        public void setManagerFeeB(BigDecimal managerFeeB) {
            this.managerFeeB = managerFeeB;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }
}
