Задача 1
Написать тест, который будет отправлять сообщения в брокер и принимать в разных режимах как сессий (AUTO_ACK, TRANSACTED, etc.),
так и сообщений (персистентные и неперсистентные).
Оформить итоговый файл с результатами записи и чтения в каждом режиме.
Объяснить полученные результаты.
Предполагаемое время решения 6-10 часов.

Использовать: tcp://localhost:61616

Неперсистентные                                                                                                                           
Session.SESSION_TRANSACTED - 27 нс (session.commit())                                                                                     
Session.AUTO_ACKNOWLEDGE - 22 нс                                                                                                          
Session.DUPS_OK_ACKNOWLEDGE - 23 нс (message.acknowledge())                                                                               
Session.CLIENT_ACKNOWLEDGE - 23 нс                                                                                                        

Персистентные                                                                                                                             
Session.SESSION_TRANSACTED - 49 нс (session.commit())
Session.AUTO_ACKNOWLEDGE - 30 нс                                                                                                          
Session.DUPS_OK_ACKNOWLEDGE - 36 нс                                                                                                       
Session.CLIENT_ACKNOWLEDGE - 42 нс (message.acknowledge())                                                                                


Задача 2
В задаче 1 использовать embedded брокер и VM-коннектор, после чего снова проверить все режимы.
Сравнить скорость работы по сравнению с использованием tcp коннектора. Объяснить полученные результаты.
Предполагаемое время решения 4-6 часов.

использовать: vm://broker

Неперсистентные
Session.SESSION_TRANSACTED - 30 нс (session.commit())
Session.AUTO_ACKNOWLEDGE - 21 нс
Session.DUPS_OK_ACKNOWLEDGE - 22 нс (message.acknowledge())
Session.CLIENT_ACKNOWLEDGE - 24 нс

Персистентные
Session.SESSION_TRANSACTED - 42 нс (session.commit())
Session.AUTO_ACKNOWLEDGE - 26 нс
Session.DUPS_OK_ACKNOWLEDGE - 26 нс
Session.CLIENT_ACKNOWLEDGE - 27 нс (message.acknowledge())


Результаты

При использовании vm-коннектора персистентные сообщения отрабатывают быстрее, неперсистентные сообщения 
отрабатывают всегда примерно одинакого
