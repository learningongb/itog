# 3
*Подключить дополнительный репозиторий MySQL. Установить любой пакет
из этого репозитория.*

Установил mysql-apt-config со страницы https://dev.mysql.com/downloads/repo/apt/ и установил его.
Далее из него устанавливал дополнительный пакет.

    sudo su
    dpkg -i mysql-apt-config_0.8.29-1_all.deb
    apt update
    apt install mysql-workbench-community

# 4
*Установить и удалить deb-пакет с помощью dpkg.*

Установка и удаление mysql-apt-config из deb файла

        dpkg -i mysql-apt-config_0.8.29-1_all.deb
        dpkg -r mysql-apt-config


