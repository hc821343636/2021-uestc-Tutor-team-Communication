#include<stdio.h>
#include<stdlib.h>
  
  
  //统计字母的个数 
  void CountLetter(FILE *fp, int data[])
  {
      char ch=fgetc(fp); // 读取文件的第一个字符 
      while(ch!=EOF) // 当读取到的不是文件结束符EOF则继续读取下一个 
     {
         if(ch>='A' && ch<='Z') // 大写字母转小写 
             ch+=32;
         if(ch>='a' && ch<='z') // 统计字母个数             
 data[ch-'a']++; // 当前字母个数加一 
         ch=fgetc(fp); // 读取文件的下一个字符 
     }
 }
 
 //打印统计字母的结果到屏幕上 
 void PrintCount(int data[]) {
     int i=26;
     int sum = 0; // 字母总的个数 
     
     while(i--)
     {
         sum+=data[i]; // 计算字母的总个数 
     }
     printf("统计字母频率结果(共%d个字母)：\n",sum);
     for(i=0; i<26; i++)
     {
         printf("%c=%d   %.2f%%   \t",'a'+i,data[i],data[i]*1.0/sum * 100); // 输出字母的频率 
         if( (i+1)%4 == 0 ) // 每输出4个后换一行 
             printf("\n"); // 换行 
     }
 }
 
 
 int main()
 {
     FILE *fp; // 文件指针
     int data[26] = {0}; // 字母统计数组 
 
     fp=fopen("output.txt","r"); // 以只读的方式打开文件，如果D盘下没有文件"A.txt"则会打开失败 
     if(fp==NULL) // 判断文件是否打开成功 
    {   
        printf("无法打开文件！\n");
         exit(1); // 退出程序  需要头文件<stdlib.h> 
     }
     
     CountLetter(fp, data); //统计字母的个数 
     PrintCount(data); // 打印统计结果
 
     fclose(fp); // 关闭数据文件 
     return 0;
 }
