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
            
            switch bmi {
                case 0.0 ..< 18.5 :
                    body = "저체중"
                case 18.5 ..< 23 :
                    body = "정상"
                case 23 ..< 25 :
                    body = "과체중"
                case 25 ..< 30 :
                    body = "비만"
                case 30 ..< 35 :
                    body = "고도 비만"
                default :
                    body = "초고도비만"
            }
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
}
