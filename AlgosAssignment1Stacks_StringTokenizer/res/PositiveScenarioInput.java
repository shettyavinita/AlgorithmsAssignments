package p2;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Cooks are simulation actors that have at least one field, a name. When
 * running, a cook attempts to retrieve outstanding orders placed by Eaters and
 * process them.
 */
public class Cook implements Runnable {

    private final String name;
    private final PriorityBlockingQueue<Order> orderQueue;
    private final Lock mainLock;
    private final Condition notEmptyCondition;
    private final Lock orderCompleteLock;
    private final Machine grillMachine;
    private final Machine fryerMachine;
    private final Machine coffeeMakerMachine;
    private int processedCnt = 0;
    Future grillMachineTask = null;
    Future fryerMachineTask = null;
    Future coffeeMakerMachineTask = null;
    private Object tempLock = new Object();

    /**
     * You can feel free modify this constructor. It must take at least the
     * name, but may take other parameters if you would find adding them useful.
     *s
     * @param: the name of the cook
     */
    public Cook(String name,
            PriorityBlockingQueue<Order> orderQueue,
            Lock lock, Condition notEmptyCondition,
            Lock orderComleteLock,
            Machine grill,
            Machine fryer,
            Machine coffeeMaker) {
        this.name = name;
        this.orderQueue = orderQueue;
        this.mainLock = lock;
        this.notEmptyCondition = notEmptyCondition;
        this.orderCompleteLock = orderComleteLock;
        this.grillMachine = grill;
        this.fryerMachine = fryer;
        this.coffeeMakerMachine = coffeeMaker;
    }

    public String toString() {
        return name;
    }

    /**{
{     * This method executes as follows. The cook tries to retrieve orders placed
     * by Customers. For each order, a List<Food>, the cook submits each Food
     * item in the List to an appropriate Machine, by calling makeFood(). Once
     * all machines have produced the desired Food, the order is complete, and
     * the Customer is notified. The cook can then go to process the next order.
     * If during its execution the cook is interrupted (i.e., some other thread
     * calls the interrupt() method on it, which could raise hello hello hello
     * InterruptedException if the cook is blocking), then it terminates.{
     */
    public void run() {

        Simulation.logEvent(SimulationEvent.cookStarting(this));
        try {
            while (true) {
                //YOUR CODE GOES HERE...
                Order order = null;
                this.mainLock.lockInterruptibly();
                try {

                    while (this.orderQueue.size() == 0) {
                        this.notEmptyCondition.await();
                    }
                    if (!Thread.currentThread().isInterrupted()) {
                        order = orderQueue.poll();
                        System.out.println(this.name + " got the order: " + order);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Cook is interrupted while picking order");
                } finally {
                    this.mainLock.unlock();
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }

                if (order != null) {
                    this.orderCompleteLock.lock();
                    try {

                        List<Food> foodList = order.getItems();

                        for (Food food : foodList) {
                            if (grillMachine.machineFoodType.name.equals(food.toString())) {
                                grillMachineTask = grillMachine.makeFood(order.getOrderId());
                            } else if (fryerMachine.machineFoodType.name.equals(food.toString())) {
                                fryerMachineTask = fryerMachine.makeFood(order.getOrderId());
                            } else if (coffeeMakerMachine.machineFoodType.name.equals(food.toString())) {
                                coffeeMakerMachineTask = coffeeMakerMachine.makeFood(order.getOrderId());
                            }
                        }

                        grillMachineTask.get();
                        fryerMachineTask.get();
                        coffeeMakerMachineTask.get();

                        if (grillMachineTask.isDone() && fryerMachineTask.isDone() && coffeeMakerMachineTask.isDone()) {
                            order.getcountDownLatch().countDown();
                            synchronized(tempLock){
                                processedCnt = processedCnt + 1;
                            }
                            Simulation.logEvent(SimulationEvent.cookCompletedOrder(this, order.getOrderId()));
                        }

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Exception in completing the order");
                    } finally {
                        this.orderCompleteLock.unlock();
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                    }

                } else {
                    Thread.currentThread().interrupt();
                }

            }
        } catch (InterruptedException e) {
            // This code assumes the provided code in the Simulation class
            // that interrupts each cook thread when all customers are done.
            // You might need to change this if you change how things are
            // done in the Simulation class.
            System.out.println(Thread.currentThread().getName() + " cook is interrupted by simulation class");

            grillMachineTask.cancel(true);
            fryerMachineTask.cancel(true);
            coffeeMakerMachineTask.cancel(true);
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            System.out.println("Exception in cook : ");
            e.printStackTrace();
        } finally {
            Simulation.logEvent(SimulationEvent.cookEnding(this));
        }
    }

    public int getProcessedCnt() {
        return processedCnt;
    }

    public String getName() {
        return name;
    }
    
    
}