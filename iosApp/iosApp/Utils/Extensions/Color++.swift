//
//  Color++.swift
//  iosApp
//
//  Created by Luu Phan on 11/04/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Color: ExpressibleByStringInterpolation {
    public init(stringLiteral value: StringLiteralType) {
        self.init(.init(hexString: value))
    }
}
