package priv.ltt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.sun.java_cup.internal.internal_error;

class goodInFo{
	//�洢��Ʒ��Ϣ
	static ArrayList<String> array_goodinfo=new ArrayList<String>();
	/**
     * ����Ϊ��λ��ȡ��Ʒ��Ϣ�ļ��洢��array_goodinfo
     */
    public static void read_GoodInfo(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
           // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                array_goodinfo.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

class promotionInFo{
	//�洢�Ż���Ʒ��Ϣ(������һ)
	static ArrayList<String> array_promotioninfo=new ArrayList<String>();
	/**
     * ����Ϊ��λ��ȡ��Ʒ��Ϣ�ļ��洢��array_goodinfo
     */
    public static void read_PromotionInfo(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                array_promotioninfo.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

class purchaseInFo{
	static ArrayList<String> array_purchaseinfo=new ArrayList<String>();
	static ArrayList<Integer> array_purchaseinfo_num=new ArrayList<Integer>();
	static String[] str_goodInFo=new String[6];
	static int num=0;
	static float all_money=0;
	static float all_moneyCharge=0;
	//����������Ʒ
	public static void calculate_NormalInfo()
	{
		System.out.println("*<ûǮ׬�̵�>�����嵥*");
		
		//ȥ�����һ��"#"
		for(int i=0;i<array_purchaseinfo.size()-1;i++)
		{
			boolean ifpurchase=false;
			int num=0;
			for(int j=0;j<promotionInFo.array_promotioninfo.size();j++)
			{
				//����Ǵ�����Ʒ
				if(array_purchaseinfo.get(i).trim().equals(promotionInFo.array_promotioninfo.get(j).trim()))
					ifpurchase=true;
			}
			for(int k=0;k<goodInFo.array_goodinfo.size();k++)
			{
				str_goodInFo=goodInFo.array_goodinfo.get(k).split("\\s+",6);
				if(ifpurchase)
					num=array_purchaseinfo_num.get(i)-array_purchaseinfo_num.get(i)/3;
				else {
					num=array_purchaseinfo_num.get(i);
				}
				if(array_purchaseinfo.get(i).equals(str_goodInFo[0]))
				{
					System.out.println("���ƣ�"+str_goodInFo[1]+"��������"+array_purchaseinfo_num.get(i)+str_goodInFo[2]+"�����ۣ�"+str_goodInFo[5]+"(Ԫ)��"+"С�ƣ�"+num* Float.parseFloat(str_goodInFo[5])+"(Ԫ)��");
					all_money+=array_purchaseinfo_num.get(i)*Float.parseFloat(str_goodInFo[5]);
				}
			}
		}
	}
	//���������Ʒ
	public static void calculate_PromotionInfo()
	{
		boolean flag=true;
		//���Ҫ��Ҫ���������һ
		
		//ȥ�����һ��"#"
		for(int i=0;i<array_purchaseinfo.size()-1;i++)
		{
			//����Ǵ�����Ʒ�ļ���
			for(int j=0;j<promotionInFo.array_promotioninfo.size();j++)
			{
				if(array_purchaseinfo.get(i).trim().equals(promotionInFo.array_promotioninfo.get(j).trim()))
				 {
					if(flag)
					{
						System.out.println("������һ��Ʒ");
						flag=false;
					}
					for(int k=0;k<goodInFo.array_goodinfo.size();k++)
					{
						//�õ��ַ����ĵ�һ���ո�
						str_goodInFo=goodInFo.array_goodinfo.get(k).split("\\s+",6);
						if(array_purchaseinfo.get(i).equals(str_goodInFo[0]))
						{
							//����������Ϣ
							float forCharge=0;
							forCharge=array_purchaseinfo_num.get(i)/3;
							System.out.println("���ƣ�"+str_goodInFo[1]+"��������"+forCharge+"��");
							all_moneyCharge+=forCharge*Float.parseFloat(str_goodInFo[5]);
						}
					}
					System.out.println("��ʡ��"+all_moneyCharge+"(Ԫ)��");
					
				}
			}
			
		}
	}

	public static void calculate_AllInfo()
	{
		System.out.println("�ܼƣ�"+String.valueOf(all_money-all_moneyCharge)+"(Ԫ)��");
	}
}

public abstract class Info {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ȫ����Ʒ��Ϣ
		String file_GoodInfo = "goodInfo.txt";
		goodInFo.read_GoodInfo(file_GoodInfo);
		for (int i = 0; i < goodInFo.array_goodinfo.size(); i++) {
			System.out.println(goodInFo.array_goodinfo.get(i));
		}
		//�Ż���Ʒ��Ϣ
		String file_PromotionInfo = "promotionInfo.txt";
		promotionInFo.read_PromotionInfo(file_PromotionInfo);
		for (int i = 0; i < promotionInFo.array_promotioninfo.size(); i++) {
			System.out.println(promotionInFo.array_promotioninfo.get(i));
		}
		//������Ʒ��Ϣ
		BufferedReader bf_purchaseinfo=new BufferedReader(new InputStreamReader(System.in));
		String str_purchaseinfo=null;
		int all_num=0;
		
		try {
			//��һ��������������
			//�����ʽITEM000001��ITEM000005-2
			//���Զ������룬ÿ��һ����Ʒ��"#"��ʾ����
			do {
				int count=1;
				str_purchaseinfo=bf_purchaseinfo.readLine();
				if(str_purchaseinfo.length()>10)
				{
					String[] split_purchaseinfo;
					split_purchaseinfo=str_purchaseinfo.split("-");
					str_purchaseinfo=split_purchaseinfo[0];
					count=Integer.parseInt(split_purchaseinfo[1]);
				}
				if(purchaseInFo.array_purchaseinfo.contains(str_purchaseinfo))
				{
					for(int i = 0;i < purchaseInFo.array_purchaseinfo_num.size(); i++){
			            if(purchaseInFo.array_purchaseinfo.get(i).equals(str_purchaseinfo))
			            {
			            	all_num=purchaseInFo.array_purchaseinfo_num.get(i);
			            }
			        }//����λ�õ�����
					purchaseInFo.array_purchaseinfo_num.set(purchaseInFo.array_purchaseinfo.indexOf(str_purchaseinfo), ++all_num);
					
				}
				else {
					//���򲻰��������룬�޸�numֵΪ1
					purchaseInFo.array_purchaseinfo.add(str_purchaseinfo);
					purchaseInFo.array_purchaseinfo_num.add(count);
					
				}
			} while (!str_purchaseinfo.equals("#"));//���һ����ƷΪ"#"
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		purchaseInFo.calculate_NormalInfo();
		purchaseInFo.calculate_PromotionInfo();
		purchaseInFo.calculate_AllInfo();
		
	}
	
}
