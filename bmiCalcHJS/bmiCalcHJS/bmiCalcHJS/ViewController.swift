//
//  ViewController.swift
//  bmiCalcHJS
//
//  Created by 소프트웨어컴퓨터 on 2021/11/20.
//  Copyright © 2021 kr.ac.induk.hjs. All rights reserved.
//

import UIKit
import Foundation

class ViewController: UIViewController {
    
    @IBOutlet weak var txtHeight: UITextField!
    @IBOutlet weak var txtWeight: UITextField!
    @IBOutlet weak var lblResult: UILabel!
    
    @IBOutlet weak var buttonBack: UIButton!


    @IBOutlet var backView: UIView!
    
    @IBAction func calcBmi(_ sender: UIButton) {
        if txtHeight.text == "" || txtWeight.text == "" {
            print("Input error!")
            lblResult.text = "키/체중을 빠짐없이 입력해주세요"
            return
        } else {
            let height = Double(txtHeight.text!)!
            let weight = Double(txtWeight.text!)!
            let bmi = weight / (height * height * 0.0001)
            let shortenedBmi = String(format: "%.1f", bmi)
            var body = ""
            var color = UIColor.white
            
            switch bmi {
                case 0.0 ..< 18.5 :
                    color = UIColor(displayP3Red: 0.1, green: 0.5, blue: 0.4, alpha: 1.0)
                    body = "저체중"
                case 18.5 ..< 23 :
                    color = UIColor(displayP3Red: 0.2, green: 0.3, blue: 0.5, alpha: 1.0)
                    body = "정상"
                case 23 ..< 25 :
                    color = UIColor(displayP3Red: 0.3, green: 0.0, blue: 1.0, alpha: 1.0)
                    body = "과체중"
                case 25 ..< 30 :
                    color = UIColor(displayP3Red: 0.5, green: 0.0, blue: 0.0, alpha: 1.0)
                    body = "비만"
                case 30 ..< 35 :
                    color = UIColor(displayP3Red: 0.7, green: 0.0, blue: 0.0, alpha: 1.0)
                    body = "고도 비만"
                default :
                    color = UIColor(displayP3Red: 1.0, green: 0.0, blue: 0.0, alpha: 1.0)
                    body = "초고도비만"
            }
            lblResult.backgroundColor = color
            lblResult.clipsToBounds = true
            lblResult.layer.cornerRadius = 10
            lblResult.text = " BMI: \(shortenedBmi), 판정: \(body)"
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        buttonBack.clipsToBounds = true
        buttonBack.layer.cornerRadius = 0.3 * buttonBack.bounds.size.height
        buttonBack.layer.borderWidth = 2
        buttonBack.layer.borderColor = UIColor.black.cgColor        // Do any additional setup after loading the view.
        
        txtHeight.keyboardType = .decimalPad
        txtWeight.keyboardType = .decimalPad
    }
    
    @IBAction func scMF(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            
        } else {
            
        }
    }
    
    
    @IBAction func SwitchOnOff(_ sender: UISwitch) {
        if sender.isOn {
            backView.backgroundColor = .systemYellow
        } else {
            backView.backgroundColor = .orange
        }
    }
}
