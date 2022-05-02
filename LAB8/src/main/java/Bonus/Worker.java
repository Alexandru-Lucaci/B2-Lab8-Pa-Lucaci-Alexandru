package Bonus;

import com.github.javafaker.Faker;

public class Worker implements Runnable{
    private static int id=0;
    private Integer count;

    public Worker(Integer count){
        this.count=count;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " start:" +count);
        try{
//            CityBonusDAO oras=new CityBonusDAO();
//            Thread.sleep(100);
            CityBonusDAO oras=new CityBonusDAO();
            for(int i=0;i<800;i++)
            {


                Faker fake=new Faker();
                id++;
                oras.create(fake.address().cityName());
//                System.out.println();
            }

        }catch(Exception e)
        {
//            e.printStackTrace();
            System.out.println("thread finished");
        }
        System.out.println(Thread.currentThread().getName()+ " end:" +count);

    }
    @Override
    public String toString(){
        return count.toString();
    }
}
