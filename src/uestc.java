import java.io.*;
import java.util.*;
class uestc{
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();//开始时间戳
        File file_source = new File(args[0]);
        File file_result = new File("result.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file_source));//将输入的字节流转换成字符流
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//将字符流添加到缓冲流
        // 新建HashMap，汉字为Character类型，次数为Integer类型
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        String str=null;
        try{
            //检测原文件是否存在
            if (!file_source.exists()) {
                System.out.println(args[0]+"不存在！");
                System.exit(0);
            }
            //检测结果文件是否已经存在
            if (file_result.exists()) {
                System.out.println("result.txt已经存在！");
                System.exit(0);
            }
            while ((str = bufferedReader.readLine()) != null){
                byte[] bytes = str.getBytes("GBK");//转码
                for (int i=0;i<(bytes.length/2) ;i++ ){
                    if (bytes[2*i]<-95 || bytes[2*i]>-87) {//首码小于-95，或大于-87，认为是汉字
                        if(map.containsKey(str.charAt(i)) ){//看数组中否已有该元素
                            Integer tempInt = (Integer)map.get(str.charAt(i));//获取该汉字的次数，并+1
                            tempInt += 1;
                            map.put(str.charAt(i), tempInt);//将汉字及其出现次数重新加入到map中，并且会覆盖相同内容的键
                        }else map.put(str.charAt(i), 1);//没有该元素，则加入
                    }
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        PrintWriter output = new PrintWriter(file_result);//创建写对象
        List<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character,Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character,Integer>>() {// 根据value排序
            //降序排序
            public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character,Integer> o2) {
                double result = o2.getValue() - o1.getValue();
                if (result > 0)
                    return 1;
                else if (result == 0)
                    return 0;
                else
                    return -1;
            }
        });
        Iterator<Map.Entry<Character,Integer>> iter = list.iterator();//获取List集合的迭代器,Map.Entry<K, V>为迭代元素的类型
        while(iter.hasNext()){
            Map.Entry<Character,Integer> item = iter.next();
            Character key = item.getKey();
            Integer value = item.getValue();
            //System.out.println( key + ":" + value);
            output.println( key + ":" + value);
        }
        output.close();//.close()方法关闭文件，如果不关闭，数据不能保存在文件中
        long end = System.currentTimeMillis();//结束时间戳
        System.out.println("共有"+map.size()+"个不同的汉字");
        System.out.println("耗时：" + (end - start) + "毫秒");
    }
}
