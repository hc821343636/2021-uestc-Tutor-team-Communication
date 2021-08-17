#include<stdio.h>
#include<stdlib.h>
  
  
  //ͳ����ĸ�ĸ��� 
  void CountLetter(FILE *fp, int data[])
  {
      char ch=fgetc(fp); // ��ȡ�ļ��ĵ�һ���ַ� 
      while(ch!=EOF) // ����ȡ���Ĳ����ļ�������EOF�������ȡ��һ�� 
     {
         if(ch>='A' && ch<='Z') // ��д��ĸתСд 
             ch+=32;
         if(ch>='a' && ch<='z') // ͳ����ĸ����             
 data[ch-'a']++; // ��ǰ��ĸ������һ 
         ch=fgetc(fp); // ��ȡ�ļ�����һ���ַ� 
     }
 }
 
 //��ӡͳ����ĸ�Ľ������Ļ�� 
 void PrintCount(int data[]) {
     int i=26;
     int sum = 0; // ��ĸ�ܵĸ��� 
     
     while(i--)
     {
         sum+=data[i]; // ������ĸ���ܸ��� 
     }
     printf("ͳ����ĸƵ�ʽ��(��%d����ĸ)��\n",sum);
     for(i=0; i<26; i++)
     {
         printf("%c=%d   %.2f%%   \t",'a'+i,data[i],data[i]*1.0/sum * 100); // �����ĸ��Ƶ�� 
         if( (i+1)%4 == 0 ) // ÿ���4����һ�� 
             printf("\n"); // ���� 
     }
 }
 
 
 int main()
 {
     FILE *fp; // �ļ�ָ��
     int data[26] = {0}; // ��ĸͳ������ 
 
     fp=fopen("output.txt","r"); // ��ֻ���ķ�ʽ���ļ������D����û���ļ�"A.txt"����ʧ�� 
     if(fp==NULL) // �ж��ļ��Ƿ�򿪳ɹ� 
    {   
        printf("�޷����ļ���\n");
         exit(1); // �˳�����  ��Ҫͷ�ļ�<stdlib.h> 
     }
     
     CountLetter(fp, data); //ͳ����ĸ�ĸ��� 
     PrintCount(data); // ��ӡͳ�ƽ��
 
     fclose(fp); // �ر������ļ� 
     return 0;
 }
