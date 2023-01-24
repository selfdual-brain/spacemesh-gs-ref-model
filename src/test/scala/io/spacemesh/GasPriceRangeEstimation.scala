package io.spacemesh

object GasPriceRangeEstimation {

  def main(args: Array[String]): Unit = {
    //we are using Apple M1 Max processor specs
    //8 turbo cores cores and 2 slow cores
    val m1TotalCyclesPerSecond = 8 * 3.2e9 + 2 * 2.0e9

    //M1 Max maximal power consumption [watts]
    val m1ProcessorPowerConsumption = 30

    // USD per 1 kWh (for business consumers)
    val electricityCostInCanada = 0.098

    // 1 hour = 3600 second; we also need to convert from kiloWatts to Watts
    val electricityCostOfM1RunningFor1second = electricityCostInCanada/3600/1000 * m1ProcessorPowerConsumption

    //1 SMESH = 1000 000 000 smidges
    val smeshGranularity = 10e9

    //calculation for Genesis day
    //we assume 1 SMESH = 0.25 USD
    val costOfOneComputationCycleAsUSDGenesis = electricityCostOfM1RunningFor1second / m1TotalCyclesPerSecond
    val priceOfOneSmhAtGenesis = 0.25
    val priceOfOneSmidgeInUSDGenesis = priceOfOneSmhAtGenesis / smeshGranularity
    val gasPriceEstimationGenesis: Double = costOfOneComputationCycleAsUSDGenesis / priceOfOneSmidgeInUSDGenesis

    //calculation for Genesis + 15 years
    //we assume computing technology advanced so much that computing efficiency (processing power per 1 watt)
    //is 10 times better than for M1 Max processor
    //additionally, we assume Spacemesh blockchain is very successful
    //and the price of 1 SMESH is 100000 dollars
    val priceOfOneSmhIn15Years = 100000.0
    val cpuTechnologyBoost = 10
    val costOfOneComputationCycleAsUSDIn15Years: Double = costOfOneComputationCycleAsUSDGenesis / cpuTechnologyBoost

    //gas price estimated as cost of one computation cycle
    //but here we express this cost in SMIDGES
    val priceOfOneSmidgeInUSDIn15Years = priceOfOneSmhIn15Years / smeshGranularity
    val gasPriceEstimationIn15Years: Double = costOfOneComputationCycleAsUSDIn15Years / priceOfOneSmidgeInUSDIn15Years

    println(s"----------- known data ------------")
    println(s"M1 Max processing speed [cycles per second]: $m1TotalCyclesPerSecond")
    println(s"Electricity cost in Canada for business consumers [USD per 1 kWh]: $electricityCostInCanada")
    println(s"M1 Max power consumption [W]: $m1ProcessorPowerConsumption")
    println(s"Electricity cost of running M1 max at full speed for 1 second [USD]: $electricityCostOfM1RunningFor1second")
    println(s"Cost of 1 computation cycle at Genesis [USD]: $costOfOneComputationCycleAsUSDGenesis")

    println(s"----------- assumptions and predictions ------------")
    println(s"assumed SMESH price on global market at Genesis [USD]: $priceOfOneSmhAtGenesis")
    println(s"assumed SMESH price on global market 15 years from now [USD]: $priceOfOneSmhIn15Years")
    println(s"assumed computing technology boost over next 15 years: $cpuTechnologyBoost")

    println(s"----------- results (when using current approach)------------")
    println(s"gas price at Genesis (with gas metering precision 1000/1): ${gasPriceEstimationGenesis * 1000}")
    println(s"gas price at Genesis (with gas metering precision 1/1): $gasPriceEstimationGenesis")
    println(s"gas price in 15 years (with gas metering precision 1000/1): ${gasPriceEstimationIn15Years * 1000}")
    println(s"gas price in 15 years (with gas metering precision 1/1): $gasPriceEstimationIn15Years")

    println(s"----------- results (with fixed scaling 2^40 applied)------------")
    val scale40 = math.pow(2, 40)
    println(s"gas price at Genesis (with gas metering precision 1000/1): ${gasPriceEstimationGenesis * 1000 * scale40}")
    println(s"gas price at Genesis (with gas metering precision 1/1): ${gasPriceEstimationGenesis * scale40}")
    println(s"gas price in 15 years (with gas metering precision 1000/1): ${gasPriceEstimationIn15Years * 1000 * scale40}")
    println(s"gas price in 15 years (with gas metering precision 1/1): ${gasPriceEstimationIn15Years * scale40}")

    println(s"----------- results (with fixed scaling 2^52 applied)------------")
    val scale52 = math.pow(2, 52)
    println(s"gas price at Genesis (with gas metering precision 1000/1): ${gasPriceEstimationGenesis * 1000 * scale52}")
    println(s"gas price at Genesis (with gas metering precision 1/1): ${gasPriceEstimationGenesis * scale52}")
    println(s"gas price in 15 years (with gas metering precision 1000/1): ${gasPriceEstimationIn15Years * 1000 * scale52}")
    println(s"gas price in 15 years (with gas metering precision 1/1): ${gasPriceEstimationIn15Years * scale52}")

  }

}
