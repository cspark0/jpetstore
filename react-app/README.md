
�� ������Ʈ�� create-react-app�� ���� ������ ��
`npm run eject` ������ ���� ���� ���� �� ��ũ��Ʈ ���ϵ��� �����  

### ���� ����
- package.json 
    - "proxy": "http://localhost:8088" �Ӽ� �߰� (back-end server �ּ� ����)
  
- config/webpack.config.js    
    - 203~208�� : �� �������� �����ϱ� ���� js ���ϰ� �̸� ���� --> ���� build/static/js �Ʒ��� bundle ���� ����  
    - 217,221�� : bundle ���ϰ� chunk ������ �̸� ���� (�ؽ� �� ����)   
    
### production build ���� ���
 `npm install`  
 `npm run build`
 
